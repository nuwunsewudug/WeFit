<template>
  <div>
    <h1>homeview</h1>
    <button @click='logout' v-if="isLoggedIn"  >로그아웃</button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  computed: {
        ...mapGetters(['isLoggedIn']),
  methods: {
    logout(){
      console.log("asd")
    }

  },
}
}
</script>

<style>

</style>