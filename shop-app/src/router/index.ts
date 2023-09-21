import { createRouter, createWebHistory, type RouteLocationNormalized } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import usersService from '@/services/UsersService'
import { ConstantKey } from '@/data/ConstantKey'

const productViewName = 'product'
const loginViewName = 'login'
const indexViewName = 'index'
const homeViewName = 'home'
const SignUpViewName = 'signUp'
const notCheckLogin: string[] = [
  productViewName,
  loginViewName,
  indexViewName,
  homeViewName,
  SignUpViewName
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: homeViewName,
      component: HomeView
    },
    {
      path: '/index',
      name: indexViewName,
      component: HomeView
    },
    {
      path: '/product',
      name: productViewName,
      component: () => import('../views/ProductView.vue')
    },
    {
      path: '/login',
      name: loginViewName,
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/users',
      name: 'users',
      component: () => import('../views/SignUp/SignUpView.vue')
    },
    {
      path: '/cart',
      name: 'cart',
      component: () => import('../views/CartView.vue')
    },
    {
      path: '/signUp',
      name: SignUpViewName,
      component: () => import('../views/SignUp/SignUpView.vue')
    }
  ]
})

router.beforeEach(loginCheck)

//檢查是否有登入或逾期
async function loginCheck(to: RouteLocationNormalized, from: RouteLocationNormalized) {
  try {
    if (notCheckLogin.includes(to.name!.toString())) {
      return
    }
    //檢查是否登入
    const token = usersService.getUsers()?.token
    if (!token) {
      sessionStorage.setItem(ConstantKey.LOGIN_SESSION_MSG, '請登入使用者帳號')
      return '/login'
    }
    //檢查是否過期
    const isVerify = await usersService.verifyToken(token)
    if (!isVerify) {
      usersService.logout()
      sessionStorage.setItem(ConstantKey.LOGIN_SESSION_MSG, '請登入使用者帳號')
      return '/login'
    }

    return
  } catch (error) {
    console.error('loginCheck error', error)
    sessionStorage.setItem(ConstantKey.LOGIN_SESSION_MSG, '發生錯誤請重新登入')
    return '/login'
  }
}

export default router
