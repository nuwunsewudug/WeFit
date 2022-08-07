<template>
   <div class="interestmodal">
    <div class="white-bg">
    <p>관심사를 선택해주세요</p>
    <table>
    <tr>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
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