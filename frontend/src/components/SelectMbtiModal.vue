<template>
   <div class="mbtimodal">
   <div class="white-bg">
    <p>MBTI를 선택해 주세요</p>
    <table>
    <tr>
    <td>
    <input type = "checkbox" class = "item"  value = "ISTJ"> ISTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "ISTP"> ISTP
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "ISFJ"> ISFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" value ="ISFP"> ISFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" value = "INTJ"> INTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "INTP"> INTP
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "INFJ"> INFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "INFP"> INFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" value = "ESTJ"> ESTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "ESTP"> ESTP
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "ESHJ"> ESHJ
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "ESFP"> ESFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" value = "ENTJ"> ENTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" value = "ENTP"> ENTP 
    </td>
    <td>
    <input type = "checkbox" class = "item" value= "ENFJ"> ENFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" value ="ENFP"> ENFP
    </td>
    </tr>
    </table>
   </div>
   </div>
</template>

<script>
export default{

    
};
</script>

<style scoped>
.mbtimodal {
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
table{
  height : 80%;
  width  : 80%;  
}
table{
    margin-left : 130px
}
p{
    font-size : 20px;
    font :bold;
    color:red;
    position : relative;
    text-align: center;
}
</style>