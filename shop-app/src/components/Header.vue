<script setup lang="ts">
import { onMounted, reactive, watch } from 'vue'
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
  () => init()
)

async function init() {
  let users: Users | null = usersService.getUsers()
  if (users) {
    state.name = users!.name
    state.isLogin = true
  } else {
    state.isLogin = false
    state.name = ''
  }
}

function logoutEvent() {
  usersService.logout()
  router.push('/login')
}
</script>

<template>
  <v-app-bar :elevation="1">
    <v-toolbar-title>
      <router-link class="d-flex" to="/index">
        <img alt="Vue logo" class="logo" src="@/assets/logo.svg" />
        <span class="ml-2">Shop-App</span>
      </router-link>
    </v-toolbar-title>

    <router-link v-for="item in state.headerItems" :to="item.href" custom v-slot="{ navigate }">
      <v-btn variant="text" @click="navigate">{{ item.name }}</v-btn>
    </router-link>

    <v-menu open-on-hover>
      <template v-slot:activator="{ props }">
        <v-btn v-bind="props"><v-icon icon="mdi-account" /></v-btn>
      </template>

      <v-list v-if="!state.isLogin">
        <v-list-item>
          <router-link to="/login" custom v-slot="{ navigate }">
            <v-btn variant="text" @click="navigate">登入</v-btn>
          </router-link>
        </v-list-item>
        <v-list-item>
          <router-link to="/users/add" custom v-slot="{ navigate }">
            <v-btn variant="text" @click="navigate">註冊</v-btn>
          </router-link>
        </v-list-item>
      </v-list>

      <v-list v-if="state.isLogin">
        <v-list-item>
          <router-link to="/users" custom v-slot="{ navigate }">
            <v-btn variant="text" @click="navigate">個人資料</v-btn>
          </router-link>
        </v-list-item>
        <v-list-item>
          <v-btn variant="text" @click="logoutEvent">登出</v-btn>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>

<style lang="scss" scoped>
.logo {
  display: block;
  width: 35px;
  height: 35px;
}

a {
  color: inherit;
  text-decoration: none;
}

a:hover {
  color: inherit;
  text-decoration: none;
}
</style>
