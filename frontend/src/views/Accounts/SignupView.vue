<template>
	<div class="signup">
		<h1>Login page</h1>
		<form class="signupform">
			<p>
				<label for="emailInput">이메일</label>
				<input type="text" id="emailInput" name="userEmail" class="input_text" ref="emailInput" v-model.trim="userEmail" placeholder="아이디를 입력하세요." />
			</p>
			<p>
				<label for="passwordInput">패스워드</label>
				<input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="패스워드를 입력하세요." />
			</p>
      <!-- <p>
				<label for="passwordCheck">패스워드</label>
				<input type="password" id="passwordCheck" name="userPasswordCheck" class="input_text" ref="passwordCheck" v-model.trim="userPasswordCheck" placeholder="패스워드를 확인하세요." />
			</p> -->
			<p class="buttons">
				<button @click.prevent="signup" class="button blue">회원가입</button>
				<button @click.prevent="$router.go(-1)" class="button">뒤로가기</button>
			</p>
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
        signup(event) {
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

<style>

</style>