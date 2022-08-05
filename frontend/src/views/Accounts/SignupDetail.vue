<template>
    <div class = "signupdetail">
    <div id = "inputdetail" >
    <h2>We Fit</h2>
    <hr>
    <p> 이름  <span>입력</span>  </p>
    <hr>
    <p> 이메일  <span>입력</span>  </p>
    <hr>    
    <p> 성별  </p>
    <hr>   
    <p> 닉네임   <span>입력</span> </p>
    <hr>   
    <p> 지역  <span> 입력</span> </p>
    <hr>   
    <p> MBTI    <span @click = "mbtiModalOpen = true"> 입력 </span></p>
    <hr>
    <p> 관심사   <span @click = "interestModalOpen = true">입력 </span> </p>
    <hr>
    <button id = "submitbtn" @click.prevent = "">가입하기</button>
    </div>
    <div>
        <img src = "@/assets/img.png" id = "img">
    </div>
    
   <!-- 모달창 -->
   <div class="interestmodal" v-if="interestModalOpen === true">
   <div class="white-bg">
    <p>좋아하는 것을 선택해 주세요</p>
    <div class = "box">
    <input type = "checkbox" class = "item"> 영화
    <input type = "checkbox" class = "item"> 애니
    <input type = "checkbox" class = "item"> 악기연주
    <input type = "checkbox" class = "item"> 동물
    </div>
    <div class = "box">
    <input type = "checkbox" class = "item"> 운동
    <input type = "checkbox" class = "item"> 사주
    <input type = "checkbox" class = "item"> 맛집
    <input type = "checkbox" class = "item"> 술
    </div>
    <div class = "box">
    <input type = "checkbox" class = "item"> 엔터
    <input type = "checkbox" class = "item"> 타로
    <input type = "checkbox" class = "item"> 요리
    <input type = "checkbox" class = "item"> 재태크
    </div>
    <div class = "box">
    <input type = "checkbox" class = "item"> 게임
    <input type = "checkbox" class = "item"> 음악감상  
    <input type = "checkbox" class = "item"> 독서
    <input type = "checkbox" class = "item"> 자격증
    </div>
   </div>
</div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'SignUpdetail',
    components: {

    },
    data() {
      return {
        userEmail : '',
        userMbti   : '',
        userGender : '', 
        userName   : '',
        userField  : '',
        userPhone  : '',
        userNickName : '',
        userInterestList : '',
        mbtiModalOpen : false,
        interestModalOpen: false
    }
    },
    computed: {
        ...mapGetters([''])
    },
    methods: {
     submit(){
        const userDetailData = {
        "userEmail" : this.userEmail,
        "userMbti": this.userMbti,
        "userGender": this.userGender,
        "userName": this.userName,
        "userField" : this.userField,
        "userPhone": this.userPhone,
        "userNickName" : this.userNickName,
      }
       this.$store.dispatch('signupdetail', userDetailData);
     },
     openmbtimodal(){

     },
     openinterestmodal(){

     },
    }
}
</script>

<style scoped>
.signupdetail{
    width : 100vw;
    height : 100vh;
    display: flex;
    background-color: rgba(22, 22, 22, 22);
}
p{
    font :bold;
    color : #FFFA85;
}
#inputdetail{
    
    padding-top : 6%;
    padding-left : 20%;
    font-weight: bold;
    color : white;
   
}
#img{
  padding-top : 100px;
  padding-left : 250px; 
}
#submitbtn{
    text-size-adjust: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    appearance: none;
    margin: 0;
    padding: 0;
    outline: none;
    user-select: none;
    font-family: -apple-system, BlinkMacSystemFont, Roboto, "Droid Sans", "Helvetica Neue", "Apple SD Gothic Neo", "sans-serif", sans-serif;
    display: block;
    width: 25vw;
    height: 50px;
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
span{
    padding-left : 40%;
    cursor:pointer;
}
.interestmodal {
  color : white;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.white-bg {
  background-color: #272724;
  height: 50vh;
  width : 50vw;
}
.box{
    display : flex;
}
.item{
display : flex;
width : 20%;
height : 20%;
}
</style>