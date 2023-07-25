<script setup lang="ts">
import usersService from '@/services/UsersService'
import { reactive } from 'vue'
import { ViewMsg } from '@/data/MsgEnum'
import { NetworkErrorCode } from '@/data/HttpEnum'
import type { AxiosError } from 'axios'
import { ConstantKey } from '@/data/ConstantKey'
import type LoginForm from '@/type/form/LoginForm'

let msg: string = sessionStorage.getItem(ConstantKey.LOGIN_SESSION_MSG) || ''
sessionStorage.removeItem(ConstantKey.LOGIN_SESSION_MSG)
let loginForm: LoginForm = {
  username: '',
  password: ''
}

const state = reactive({ loginForm, msg })
const loginEvent = async () => {
  try {
    state.msg = ''
    if (!(state.loginForm.username && state.loginForm.password)) {
      state.msg = '請輸入帳號及密碼!'
      return
    }
    await usersService.login(state.loginForm)
    window.location.href = '/index'
  } catch (error) {
    let axiosError: AxiosError = error as AxiosError
    if (NetworkErrorCode.Unauthorized == axiosError.response?.status) {
      state.msg = ViewMsg.InvalidUsernameOrPassword
      return
    }
    console.error('login error:', error)
    state.msg = ViewMsg.ServerError
  }
}
</script>

<template>
  <main class="main">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-3 form-signin">
          <form>
            <h1 class="h3 mb-3 fw-normal">使用者登入</h1>
            <div class="form-floating">
              <input
                id="floatingInput"
                name="username"
                class="form-control"
                placeholder="帳號"
                v-model="state.loginForm.username"
                required="true"
              />
              <label for="floatingInput">帳號</label>
            </div>
            <div class="form-floating">
              <input
                id="floatingPassword"
                name="password"
                type="password"
                class="form-control"
                placeholder="密碼"
                required="true"
                v-model="state.loginForm.password"
              />
              <label for="floatingPassword">密碼</label>
            </div>
            <div v-if="state.msg" class="alert alert-danger" role="alert">{{ state.msg }}</div>
            <button type="button" class="w-100 btn btn-lg btn-success" @click="loginEvent">
              登入
            </button>
          </form>
        </div>
      </div>
    </div>
  </main>
</template>
<style lang="scss" scoped>
.form-signin {
  max-width: 330px;
  padding: 15px;

  .form-floating:focus-within {
    z-index: 2;
  }

  input[name='username'] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  input[name='password'] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }

  .alert-danger {
    font-size: 1em;
    padding: 10px;
  }
}
</style>
