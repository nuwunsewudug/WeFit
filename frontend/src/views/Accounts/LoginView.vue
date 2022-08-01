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
				<button @click="handleLogin">Google ID로 로그인</button>
			</p>
		</form>
		<!-- <p>{{ errorMessage }}</p> -->
	</div>
</template>

<script>
import { mapGetters } from 'vuex'
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
		async handleLogin() {
      try {
        const GoogleUser = await this.$gAuth.signIn();
        console.log(GoogleUser);
      } catch (e) {
        console.error(e);
      }
    },
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