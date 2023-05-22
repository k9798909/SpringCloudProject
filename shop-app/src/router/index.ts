import { createRouter, createWebHistory, type RouteLocationNormalized } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import usersService from '@/services/UsersService'
import { ConstantKey } from '@/data/ConstantKey'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/index',
      name: 'index',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/product',
      name: 'product',
      component: () => import('../views/ProductView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/usersInfo',
      name: 'usersInfo',
      component: () => import('../views/UserInfoView.vue'),
      beforeEnter: loginCheck
    }
  ]
})

router.beforeEach(tokenOverdue)

//驗證token是否逾期
async function tokenOverdue(to: RouteLocationNormalized, from: RouteLocationNormalized) {
  try {
    const token = usersService.getUsers().token

    if (!token) {
      return
    }

    const isVerify: boolean = await usersService.verifyUsers()
    if (!isVerify) {
      sessionStorage.setItem(ConstantKey.LOGIN_SESSION_MSG, '登入逾期請重新登入')
      return '/login'
    }
    return
  } catch (error) {
    console.error('tokenOverdue error', error)
    sessionStorage.setItem(ConstantKey.LOGIN_SESSION_MSG, '發生錯誤請重新登入')
    return '/login'
  }
}

//檢查是否有登入或逾期
async function loginCheck(to: RouteLocationNormalized, from: RouteLocationNormalized) {
  try {
    const token = usersService.getUsers().token

    if (!token) {
      sessionStorage.setItem(ConstantKey.LOGIN_SESSION_MSG, '請登入帳號')
      return '/login'
    }

    const isVerify: boolean = await usersService.verifyUsers()
    if (!isVerify) {
      sessionStorage.setItem(ConstantKey.LOGIN_SESSION_MSG, '登入逾期請重新登入')
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
