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
				<input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="패스워드를 입력하세요." />
			</p>
            <div>                
                <input type = "checkbox"> <label class = "text">i agree 어쩌구</label>
            </div>
            <div>
                <button @click.prevent="$router.push('signupdetail')"> create your account</button>
            </div>
            </form>
 </div>
</template>

<script>

</script>

<style>
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
</style>