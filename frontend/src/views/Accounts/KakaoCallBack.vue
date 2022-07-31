<template>
<div>
    test.
</div>
</template>

<script>
import { getKakaoToken, getKakaoUserInfo, refreshToken } from "@/services/login.js"
export default {
    name: 'kakaoCallBackView',
    components: {
    },
    data() {
        return {
            userEmail: '',
            userPassword: ''
        }
    },
    mounted() {
    getKakaoToken();
    getKakaoUserInfo();
    refreshToken ();
    },
    computed: {
    },
    methods: {

    }

}
</script>

<style>

</style>