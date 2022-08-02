import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/MainPage/LoginHome.vue'
import LoginView from '@/views/Accounts/LoginView.vue'
import SignupView from '@/views/Accounts/SignupView.vue'
import KakaoCallBack from '@/views/Accounts/KakaoCallBack.vue'
import findId from '@/views/Accounts/FindId.vue'
import findPw from '@/views/Accounts/FindPw.vue'
import SignUpByEmail from '@/views/Accounts/SignupByEmailView.vue'
import SigupDetail from '@/views/Accounts/SignupDetail.vue'
const routes = [
  {
    path: '/',
    name: '',
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
  {
    path: '/signup',
    name: 'SignupView',
    component: SignupView,
  },
  {
    path: '/signupbyemail',
    name: 'SignupByEmailView',
    component: SignUpByEmail,
  },
  {
    path: '/signupdetail',
    name: 'signupdetail',
    component: SigupDetail,
  },
  {
    path: '/kakaocallback',
    name: 'kakaoCallBackView',
    component: KakaoCallBack,
  },
  {
    path: '/findid',
    name: 'findid',
    component: findId,
  },
  {
    path: '/findpw',
    name: 'findpw',
    component: findPw,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
