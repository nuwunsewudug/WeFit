<template>
<div class="findpw">
		<form class="findpwform">
		<h2>비밀번호찾기</h2>
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
            <div>
                <button> 찾기</button>
            </div>
            </form>
</div>
</template>

<script>

</script>

<style scoped>
.findpw{
    height: 100vh;
    background-image: url("@/assets/loginpage.jpg");
    background-repeat : no-repeat;
    background-size : cover;
 }
h2{
    color : red;
 }
.findpwform{
  text-align:center;
  position : relative;
  top :25%;
 }
 #emailtext{
  color: white;
 }
 #passwordtext{
  color: white;
 }
</style>