import { createStore } from 'vuex'
import axios from 'axios'
import router from '@/router'

export default createStore({
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
      localStorage.setItem('token')
    },
    
    login({ commit, dispatch }, userData) {
      axios.post('url', userData)
      .then(res => {
        commit('SET_TOKEN', res.data.key)
        localStorage.setItem('token', res.data.key)
        commit('LOGIN')
        dispatch('fetchCurrentUser')
        router.push({ name: 'urlname' })
      })
      .catch(err => {
        console.error(err)
      })
    },

    fetchCurrentUser({ commit, getters, dispatch }) {
      if (getters.isLoggedIn) {
        axios({
          url: drf.accounts.currentUserInfo(),
          method: 'get',
          headers: getters.authHeader,
        })
          .then(res => commit('SET_CURRENT_USER', res.data))
          .catch(err => {
            if (err.response.status === 401) {
              dispatch('removeToken')
              router.push({ name: 'LoginView' })
            }
          })
        }
      },
    },
    modules: {
    }
})
