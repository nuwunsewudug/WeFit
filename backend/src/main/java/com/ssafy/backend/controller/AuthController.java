package com.ssafy.backend.controller;


import com.ssafy.backend.dto.LoginDto;
import com.ssafy.backend.dto.TokenDto;
import com.ssafy.backend.jwt.JwtFilter;
import com.ssafy.backend.jwt.TokenProvider;
import com.ssafy.backend.radis.RedisService;
import com.ssafy.backend.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jdk.nashorn.internal.parser.Token;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;

    private final RedisService redisService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final RedisTemplate<String, String> redisTemplate;


    public AuthController(TokenProvider tokenProvider, TokenService tokenService, RedisService redisService, AuthenticationManagerBuilder authenticationManagerBuilder, RedisTemplate redisTemplate) {
        this.tokenProvider = tokenProvider;
        this.tokenService = tokenService;
        this.redisService = redisService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        // 0726 refactoring
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(), loginDto.getUserPassword());
//
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // authentication 얻기
        Authentication authentication = tokenProvider.createAuthenticate(loginDto.getUserEmail(), loginDto.getUserPassword());

        // access token, refresh token 만들기 0726
        String accessToken = tokenService.createAccessToken(authentication);
        String refreshToken = tokenService.createRefreshToken(authentication);

        //userEmail을 key 값으로 refreshToken을 value로 설정
        redisService.setValues(loginDto.getUserEmail(),refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken + " " + refreshToken);

        return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/api/v1/re-issue")
    public ResponseEntity<?> reIssue(HttpServletRequest request) {
        
        String userEmail = tokenService.getUserEmailFromToken(request);
        // 사용자 정보가 토큰에 없는 경우
        System.out.println(userEmail);
        if (userEmail.equals("null")) {
            return new ResponseEntity<String>("존재하지 않는 사용자입니다.",HttpStatus.OK);
        }

        //사용자가 있지만 해당 사용자의 refresh 토큰이 유효하지 않다면 로그아웃 처리
        if(!tokenService.checkRefreshToken(userEmail)){
            redisService.deleteValues(userEmail); //refresh 토큰 삭제
            return new ResponseEntity<String>("세션이 만료되어 로그아웃 되었습니다",HttpStatus.OK);
        }

        // 403이 발생한 경우 -> true 리턴. 발생한 경우에만 토큰 발급함
        //아래는 발생하지 않은 경우 예외 처리임.
        //아직 유효한 토큰인데 접근한 경우
        //우리와 다른 시크릿 키를 사용해서 권한이 없는경우?(이메일로 jwt위조한 경우?) 등
        if(!tokenService.checkAccessToken(request)){
            redisService.deleteValues(userEmail);
            return new ResponseEntity<String>("잘못된 접근방식으로 로그아웃 합니다",HttpStatus.OK);
        }


        String accessToken = tokenService.reIssueAccessToken(userEmail);
//        String refreshToken = redisService.getValues(userEmail);
        // re-issue는 리프레시 토큰은 살아있고 access token 재발급을 위한 것이므로 access Token만 재발급
        TokenDto responseDto = TokenDto.builder().token(accessToken).build();

        return new ResponseEntity<TokenDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/api/v1/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.

        if(redisService.getValues(authentication.getName()) != null){
            redisService.deleteValues(authentication.getName());
        }
        String token = tokenService.getToken(request);

        Date expiration = tokenProvider.getExpiration(token);
        Date now = new Date();

        //expiration이 끝나는 시간이므로 현재 시간을 빼주어야 남은 시간이 설정됨
        redisService.setValues(token,"logout",expiration.getTime()-now.getTime(),TimeUnit.MILLISECONDS);


        return new ResponseEntity<String>("로그아웃 되었습니다",HttpStatus.OK);
    }

}
