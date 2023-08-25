import { defineStore } from 'pinia'
import type Users from '@/type/stores/Users'
import type LoginResDto from '@/type/dto/LoginResDto'

const useUsersStore = defineStore('usersStore', {
  state: () => {
    const storage: Storage = localStorage
    let users: Users | null = null

    if (storage.getItem('users')) {
      const localUsers = JSON.parse(storage.getItem('users')!) as Users
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
