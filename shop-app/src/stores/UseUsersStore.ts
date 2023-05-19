import { defineStore } from 'pinia'
import type Users from '@/type/Users'
import type LoginResDto from '@/type/dto/LoginResDto'

const useUsersStore = defineStore('users', {
  state: () => {
    return { users: null as Users | null }
  },
  getters: {
    getName: (state) => state.users?.name,
    getToken: (state) => state.users?.token,
    isLogin: (state) => state.users && state.users?.name && state.users?.token
  },
  actions: {
    login(loginRes: LoginResDto) {
      this.users = {
        ...loginRes
      }
    }
  }
})

export default useUsersStore
