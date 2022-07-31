<template>
  <div>
    <h1>homeview</h1>
    <button @click.prevent="$router.push('login')">로그인</button>
    <button @click.prevent="$router.push('signup')">회원가입</button>
    <button v-if="isLoggedIn" @click="logout" to="#" class="text-decoration-none white--text">
        Logout
    </button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  computed: {
        ...mapGetters(['isLoggedIn']),
  },
  methods: {    
    logout() {
      this.$store.dispatch('logout')
    },
  },
}
</script>

<style>

</style>