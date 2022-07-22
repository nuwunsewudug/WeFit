import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
  },
  getters: {
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
  },
  mutations: {
    Login(state) {
      state.isLogin = true
    },
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('token')
    },
    
    login({ commit, dispatch }, userData) {
      axios.post()
    }
  },
  modules: {
  }
})
