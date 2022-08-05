<template>
 <div class="signupbyemail">
		<form class="signupbyemailform">
		<h2>회원가입 페이지</h2>
			<p>
				<div>
				<label for="emailInput" id = "emailtext" class = "text">email</label>
				</div>
				<input type="text" id="emailInput" name="userEmail" class="input_text" ref="emailInput" v-model.trim="userEmail" placeholder="아이디를 입력하세요." />
			</p>
			<p>
				<div>
				<label for="passwordInput" id = "passwordtext" class = "text">password</label>
				</div>
				<input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="패스워드를 입력하세요." />
			</p>
            <p>
				<div>
				<label for="passwordInput" id = "passwordtext" class = "text">password confirmation</label>
				</div>
				<input type="password" id="passwordInput" name="userPasswordCheck" class="input_text" ref="passwordInput" v-model.trim="userPasswordCheck" placeholder="패스워드를 확인하세요." />
			</p>
			<!-- 밑의 체크박스 넣어야합니까? -->
            <div>                
                <input type = "checkbox"> <label>i agree 어쩌구</label>
            </div>
            <div>
                <button @click.prevent = "emailSignup" id = "btn"> create your account</button>
            </div>
            </form>
 </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'SignupbyemailView',
    components: {

    },
    data() {
        return {
            userEmail: '',
            userPassword: '',
            userPasswordCheck:'',
        }
    },
    computed: {
        ...mapGetters([''])
    },
    methods: {
      emailSignup(event) {
      event.preventDefault()
      const userData = {
        "userEmail": this.userEmail,
        "userPassword": this.userPassword,
        "userPasswordCheck": this.userPasswordCheck
      }
	  if(this.userPassword !='' &&this.userPassword == this.userPasswordCheck )
	  {
		/* 
		회원가입 유저 플로우가 회원가입 -> 상세정보 입력 후 한번에 회원가입부터 디테일까지 처리?  
		아니면 회원가입 먼저 DB에서 처리하고 그다음 상세내용을 입력하게 되는가?  
		회원가입 먼저 처리할 경우 중간에 유저가 나가버릴 경우 어떻게 되는지? 
		내 생각 -> Api에 요청을 디테일까지 한번에 보내기.
		*/
		this.$router.push('signupdetail');
        this.$store.dispatch('signup', userData);
	  }
	  else{
		alert('비밀번호를 확인하세요');
	  }
      // dispatch
    },
    }
}
</script>

<style scoped>
.signupbyemail{
    height: 100vh;
    background-image: url("@/assets/loginpage.jpg");
    background-repeat : no-repeat;
    background-size : cover;
 }
.signupbyemailform{
  text-align:center;
  position : relative;
  top :25%;
}
h2{
    color :red;
}
.text{
    color :white;
}
.input_text{
  border-radius: 8px;
  width : 24%;
  height: 40px;
  margin-left: 10px;
}
#btn{
    text-size-adjust: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    appearance: none;
    margin: auto;
    padding: 0;
    outline: none;
    user-select: none;
    font-family: -apple-system, BlinkMacSystemFont, Roboto, "Droid Sans", "Helvetica Neue", "Apple SD Gothic Neo", "sans-serif", sans-serif;
    display: block;
    width: 23%;
    height: 46px;
    padding-bottom: 1px;
    border-radius: 10px;
    cursor: pointer;
    color: #fff;
    font-weight: bold;
    background: #ef3e43;
    border: 0;
    margin-top: 30px;
    font-size: 17px;
}
</style>