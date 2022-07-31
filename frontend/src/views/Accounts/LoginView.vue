<template>
	<div class="login">
		<h1>Login page</h1>
		<form class="loginform">
			<p>
				<label for="emailInput">이메일</label>
				<input type="text" id="emailInput" name="userEmail" class="input_text" ref="emailInput" v-model.trim="userEmail" placeholder="아이디를 입력하세요." />
			</p>
			<p>
				<label for="passwordInput">패스워드</label>
				<input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="패스워드를 입력하세요." />
			</p>
			<p class="buttons">
				<button @click.prevent="login" class="button blue">로그인</button>
				<button @click.prevent="$router.go(-1)" class="button">뒤로가기</button>
				<button @click.prevent="$router.push('signup')">회원가입</button>
				<button @click.prevent="google" >구글 로그인</button>
				<button @click.prevent="kakaoLogin" > 카카오 로그인</button>
				<button @click.prevent="naverLogin" > 네이버 로그인</button>
			</p>
		</form>
		<!-- <p>{{ errorMessage }}</p> -->
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
.login { 
	width:800px; 
	margin:20px auto; 
	}
.loginform { 
	width:400px; 
	margin:auto; 
	}
.loginform p > label { 
	display:inline-block; 
	width:100px; 
	font-size:14px;
	padding-right:10px; 
	}
.loginform p > .input_text { 
	width:200px; 
	font-size:14px; 
	height:32px; 
	}
.buttons { 
	position:relative; 
	height:32px; 
	margin-top:20px; 
	}
.buttons > .button { 
	overflow:visible; 
	cursor:pointer; 
	min-width:125px; 
	height:32px; 
	margin:0 2px; 
	padding:0 15px; 
	line-height:32px; 
	font-size:14px; 
	border:1px 
	solid #dfdfdf; 
	background:#fff; 
	border-radius:10px; 
	}

.buttons > .button.blue { 
	color:#fff; 
	border-color:#0099d2 !important; 
	background:#0099d2 !important; }
</style>