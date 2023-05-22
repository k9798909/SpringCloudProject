import { defineStore } from 'pinia'
import type Users from '@/type/stores/Users'
import type LoginResDto from '@/type/http/dto/LoginResDto'
import { clean } from '@/type/stores/Users'

const useUsersStore = defineStore('usersStore', {
  state: () => {
    let users: Users = {
      name: '',
      token: ''
    }

    if (localStorage.getItem('users')) {
      const localUsers = JSON.parse(localStorage.getItem('users')!) as Users
      users = { ...localUsers }
    }
    return { users }
  },
  getters: {
    getUsers: (state) => state.users,
  },
  actions: {
    login(loginRes: LoginResDto) {
      this.users = { ...loginRes }
      localStorage.setItem('users', JSON.stringify(this.users))
    },
    logout() {
      clean(this.users);
      localStorage.removeItem('users')
    }
  }
})

export default useUsersStore
