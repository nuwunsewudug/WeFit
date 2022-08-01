import { createRouter, createWebHistory } from 'vue-router'
import LoginHome from '@/views/MainPage/LoginHome.vue'
import LoginView from '@/views/Accounts/LoginView.vue'
import SignupView from '@/views/Accounts/SignupView.vue'

const routes = [
  {
    path: '/Home',
    name: 'LoginHome',
    component: LoginHome
  },
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView,
  },
  {
    path: '/signup',
    name: 'SignupView',
    component: SignupView,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
