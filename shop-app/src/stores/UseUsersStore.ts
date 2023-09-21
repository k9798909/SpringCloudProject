import { defineStore } from 'pinia'
import type Users from '@/types/stores/Users'
import type LoginResDto from '@/types/dto/LoginResDto'

const useUsersStore = defineStore('usersStore', {
  state: () => {
    const storage: Storage = localStorage
    const storeUsers = storage.getItem('users')
    let users: Users | null = null
    if (storeUsers) {
      const localUsers = JSON.parse(storeUsers) as Users
      users = { ...localUsers }
    }
    return { users, storage }
  },
  getters: {
    getUsers: (state) => state.users
  },
  actions: {
    login(loginRes: LoginResDto) {
      this.users = { ...loginRes }
      this.storage.setItem('users', JSON.stringify(this.users))
    },
    logout() {
      this.users = null
      this.storage.removeItem('users')
    }
  }
})

export default useUsersStore
