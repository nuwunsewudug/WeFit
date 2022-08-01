import { createRouter, createWebHistory } from 'vue-router'
import NonLoginView from '@/views/MainPage/NonLoginHome.vue'
import LoginView from '@/views/Accounts/LoginView.vue'
import SignupView from '@/views/Accounts/SignupView.vue'

const routes = [
  {
    path: '/',
    name: 'NonLoginView',
    component: NonLoginView
  },
  // {
  //   path: '/home',
  //   name: 'HomeView',
  //   component: HomeView,
  //   beforeEnter: requireAuth()
  // },
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
