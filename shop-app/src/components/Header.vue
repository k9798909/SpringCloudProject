<script setup lang="ts">
import { onMounted, reactive, watch } from 'vue'
import { ViewMsg } from '@/data/MsgEnum'
import usersService from '@/services/UsersService'
import headerItems from '@/data/HeaderItems'
import type Users from '@/type/stores/Users'
import router from '@/router'
import useUsersStore from '@/stores/UseUsersStore'

let isLogin: boolean = false
let name: string = ''
const state = reactive({ isLogin, name, headerItems, useUsersStore })
onMounted(init)

const userStore = useUsersStore()
watch(
  () => userStore.users,
  () => {
    console.log('isLoggedIn ref changed, do something!')
    init()
  }
)

async function init() {
  try {
    let users: Users | null = usersService.getUsers()
    if (users) {
      state.name = users!.name
      state.isLogin = true
    } else {
      state.isLogin = false
      state.name = ''
    }
  } catch (error) {
    console.log('init header error:', error)
    alert(ViewMsg.ServerError)
  }
}

function logoutEvent() {
  usersService.logout()
  router.push('/login')
}
</script>

<template>
  <header>
    <div class="logo-area">
      <a href="/index">
        <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />
      </a>
    </div>
    <nav>
      <div class="link-bar">
        <router-link v-for="item in state.headerItems" :to="item.href">{{ item.name }}</router-link>
      </div>
    </nav>
    <div class="login-area" v-if="!state.isLogin">
      <router-link to="/login">登入</router-link>
      <span>|</span>
      <router-link to="/addUser">註冊</router-link>
    </div>
    <div class="login-area" v-if="state.isLogin">
      <div>
        <font-awesome-icon class="mx-1" :icon="['fas', 'user']" size="lg" /> {{ state.name }}
      </div>
      <div><button v-on:click="logoutEvent">登出</button></div>
    </div>
  </header>
</template>

<style lang="scss" scoped>
header {
  $hover-color: #999;
  $font-color: #213547;
  $bg-color: #ffffff;

  border-bottom: 1px solid rgba(60, 60, 60, 0.12);
  line-height: 1.5;
  width: 100%;
  display: flex;
  justify-content: space-between;
  background-color: $bg-color;
  -webkit-backdrop-filter: saturate(180%) blur(20px);
  backdrop-filter: saturate(180%) blur(20px);
  padding: 0.5rem 1.5rem;

  nav a,
  .login-area a {
    color: $font-color;
    transition: ease-in-out color 0.15s;
    padding: 0.3rem;
    font-size: 1rem;
  }

  .login-area span {
    padding-top: 0.2rem;
    font-size: 1rem;
  }

  nav a:hover,
  .login-area a:hover {
    color: $hover-color;
  }

  .logo-area {
    width: 5%;
    .logo {
      display: block;
      width: 35px;
      height: 35px;
      padding-top: 0.3rem;
    }
  }

  .login-area {
    width: 25%;
    padding: 0.3rem;
    color: $font-color;
    font-size: 1rem;
    display: flex;
    justify-content: flex-end;
    button {
      background-color: transparent;
      border: 0px;
      color: $font-color;
    }
    button:hover {
      color: $hover-color;
    }
  }

  nav {
    width: 70%;
    text-align: center;
    display: flex;
    justify-content: end;
    .link-bar {
      display: flex;
      justify-content: space-around;
    }

    a {
      padding: 0.3rem 2rem;
    }
  }
}
</style>
