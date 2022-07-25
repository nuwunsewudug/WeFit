import axios from 'axios'
import router from '@/router'
import drf from '@/api/drf'

export default {
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
  },
  getters: {
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    authError: state => state.authError,
  },
  mutations: {
    Login(state) {
      state.isLogin = true
    },
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_AUTH_ERROR: (state, error) => state.authError = error
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('token', token)
    },
    
    login({ commit, dispatch }, userData) {
      axios({
            url: drf.accounts.login(),
            method: 'post',
            data: userData
          })
            .then(res => {
              const token = res.data.token
              
              dispatch('saveToken', token)
              dispatch('fetchCurrentUser')
              router.push({ name: 'HomeView' })
            })
            .catch(err => {
              console.log(err)
              console.error(err)
              commit('SET_AUTH_ERROR', err)
            })
    },

    signup({ commit, dispatch }, userData) {
      axios({
        url: drf.accounts.signup(),
        method: 'post',
        data: userData
      })
        .then(res => {
          const token = res.data.key
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          router.push({ name: 'HomeView' ,params:{username:userData.username}})
        })
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },

    // fetchCurrentUser({ commit, getters, dispatch }) {
    //   if (getters.isLoggedIn) {
    //     axios({
    //       url: drf.accounts.currentUserInfo(),
    //       method: 'get',
    //       headers: getters.authHeader,
    //     })
    //       .then(res => commit('SET_CURRENT_USER', res.data))
    //       .catch(err => {
    //         if (err.response.status === 401) {
    //           dispatch('removeToken')
    //           router.push({ name: 'LoginView' })
    //         }
    //       })
    //     }
    //   },
    },
  modules: {
  }
}
