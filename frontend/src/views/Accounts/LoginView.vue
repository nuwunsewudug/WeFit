<template>
	<div class="login">
		<form class="loginform">
		<h2>로그인 페이지</h2>
			<p>
				<div>
				<label for="emailInput" id = "emailtext">your email</label>
				</div>
				<input type="text" id="emailInput" name="userEmail" class="input_text" ref="emailInput" v-model.trim="userEmail" placeholder="아이디를 입력하세요." />
			</p>
			<p>
				<div>
				<label for="passwordInput" id = "passwordtext">your password</label>
				</div>
				<input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="패스워드를 입력하세요." />
			</p>
			<p class="buttons">
				<button @click.prevent="login" class="button blue">로그인</button>

				<div><p><span @click.prevent="$router.push('findid')" id = "findid">아이디찾기</span>  <span @click.prevent="$router.push('findpw')" id = "findpw" >비밀번호찾기</span>  <span @click.prevent="$router.push('signup')" id = "signup">회원가입</span></p></div> 
				<div>
				<button @click.prevent="google" >구글 로그인</button>
				</div>
				<div>
				<button @click.prevent="kakaoLogin" > 카카오 로그인</button>
				</div>
				<div>
				<button @click.prevent="naverLogin" > 네이버 로그인</button>
				</div>
			</p>
		</form>
	</div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getAuth, signInWithPopup, GoogleAuthProvider } from "firebase/auth";
export default {
    name: 'LoginView',
    components: {
    },
    data() {
        return {
            userEmail: '',
            userPassword: ''
        }
    },
    computed: {
        ...mapGetters([''])
    },
    methods: {
    login(event) {
      event.preventDefault()
      const userData = {
        "userEmail": this.userEmail,
        "userPassword": this.userPassword
      }
      // dispatch
      this.$store.dispatch('login', userData)
    },
	google(){
		const provider = new GoogleAuthProvider();
		provider.addScope('https://www.googleapis.com/auth/contacts.readonly');
		provider.setCustomParameters({
       'login_hint': 'user@example.com'
        });
		const auth = getAuth();
        signInWithPopup(auth, provider).then((result) => {
        // This gives you a Google Access Token. You can use it to access the Google API.
        const credential = GoogleAuthProvider.credentialFromResult(result);
        const token = credential.accessToken;
        // The signed-in user info.
        const user = result.user;
		console.log(user);
		this.$store.dispatch('socialLogin', socialLoginData)
		
    // ...
     }).catch((error) => {
    // Handle Errors here.
    const errorCode = error.code;
    const errorMessage = error.message;
    // The email of the user's account used.
    const email = error.customData.email;
    // The AuthCredential type that was used.
    const credential = GoogleAuthProvider.credentialFromError(error);
    // ...
     });
	},
	kakaoLogin(){
	 Kakao.isInitialized();
	 window.Kakao.Auth.authorize({
     redirectUri: 'http://localhost:8080/kakaocallback'
     });

	 
	 
   

	 /*
	 Kakao.Auth.login({	
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
	*/
    },
	naverLogin(){
		
	}
    }
}
</script>

<style scoped>
.login{
    height: 100vh;
    background-image: url("@/assets/loginpage.jpg");
    background-repeat : no-repeat;
    background-size : cover;
 }
 .loginform{
  text-align:center;
  position : relative;
  top :25%;
 }
 h2{
	color : red;
 }
 .loginform{
 }
 #signup {
  font-weight: bold;
  color: white;
  cursor:pointer;
 }
 #findid {
  font-weight: bold;	
  color: white;
  cursor:pointer;
 }
 #findpw{
  font-weight: bold;
  color: white;
  cursor:pointer;
 }
 #emailtext{
  color: white;
 }
 #passwordtext{
  color: white;
 }
</style>