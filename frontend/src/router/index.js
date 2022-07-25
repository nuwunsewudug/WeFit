import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/Accounts/LoginView.vue'

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
