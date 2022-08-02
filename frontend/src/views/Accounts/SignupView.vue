<template>
	<div class="signup" >
    <form class="signupform">
		<h2>회원가입 페이지</h2>
      <form class = "buttons">
        <div>
				<button @click.prevent="$router.push('signupbyemail')" id = "signupbtn">이메일로 가입하기</button>
        </div>
        <div>
				<button class="button" id = "googlebtn"  @click.prevent = "googleSignup">구글 계정으로 가입</button>
        </div>
        <div>
				<button class="button" id = "kakaobtn"  @click.prevent = "kakaoSignup">카카오로 시작하기</button>
        </div>
        <div>
				<button class="button" id = "naverbtn" @click.prevent = "naverSignup">네이버로 시작하기</button>
        </div>
        <div>이미 회원이신가요? <span id = "login" @click.prevent = "$router.push('login')">로그인</span></div>
      </form>
		</form>
		<!-- <p>{{ errorMessage }}</p> -->
	</div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'SignupView',
    components: {

    },
    data() {
        return {
            userEmail: '',
            userPassword: '',
            // userPasswordCheck:'',
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
        // "userPasswordCheck": this.userPasswordCheck
      }
      // dispatch
      this.$store.dispatch('signup', userData)
    },
    }
}
</script>

<style scoped>
h2{
  text-align:center;
  color : red;
}
.signupform{
  text-align:center;
  position : relative;
  top :25%;
}
.buttons{

  }
#naverbtn{
    width: 23%;
}
.naver img{
    height:0.9rem;
    margin-right: 0.7em;
    margin-left: 0.2em;
}
#kakaobtn{
  width: 23%;
}
#googlebtn{
  width: 23%;
}
#signupbtn{
  width: 23%;
}
#login{
  font-weight: bold;
  color : red;
  cursor:pointer;
}


.signup{
    height: 100vh;
    background-image: url("@/assets/loginpage.jpg");
    background-repeat : no-repeat;
    background-size : cover;
 }

</style>