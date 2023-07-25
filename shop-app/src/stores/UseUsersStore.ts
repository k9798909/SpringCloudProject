import { defineStore } from 'pinia'
import type Users from '@/type/stores/Users'
import type LoginResDto from '@/type/dto/LoginResDto'
import { clean } from '@/type/stores/Users'

const useUsersStore = defineStore('usersStore', {
  state: () => {
    const storage: Storage = localStorage
    let users: Users = {
      username: '',
      name: '',
      token: ''
    }

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
      console.log(loginRes)
      this.users = { ...loginRes }
      this.storage.setItem('users', JSON.stringify(this.users))
    },
    logout() {
      clean(this.users)
      this.storage.removeItem('users')
    }
  }
})

export default useUsersStore
