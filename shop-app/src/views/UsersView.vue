<script setup lang="ts">
import { reactive } from 'vue'
import type AddUsersForm from '@/type/form/AddUsersForm'
const form: AddUsersForm = {
  name: '',
  birthday: new Date().toLocaleDateString('en-CA'),
  idn: '',
  email: '',
  address: '',
  username: '',
  password: '',
  chkPassword: ''
}
const state = reactive({ form })
</script>

<template>
  <main>
    <div class="container w-50">
      <div class="py-2 text-center">
        <h2>使用者資料</h2>
      </div>

      <div class="row justify-content-center">
        <form>
          <div class="row mb-3">
            <div class="col-6">
              <label for="name" class="form-label">姓名</label>
              <input
                v-model="state.form.name"
                type="text"
                class="form-control"
                id="name"
                placeholder="請輸入姓名"
                required
              />
            </div>

            <div class="col-6">
              <label for="birthday" class="form-label">出生日期</label>
              <input
                v-model="state.form.birthday"
                type="date"
                class="form-control"
                id="birthday"
                required
              />
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-6">
              <label for="idn" class="form-label">身分證字號</label>
              <input
                v-model="state.form.idn"
                type="text"
                class="form-control"
                id="idn"
                placeholder="請輸入身分證字號"
              />
            </div>
            <div class="col-6">
              <label for="email" class="form-label">Email</label>
              <input
                v-model="state.form.email"
                type="email"
                class="form-control"
                id="email"
                placeholder="請輸入電子信箱"
              />
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-12">
              <label for="address" class="form-label">地址</label>
              <input
                v-model="state.form.address"
                type="text"
                class="form-control"
                id="address"
                placeholder="請輸入地址"
                required
              />
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-12">
              <label for="username" class="form-label">帳號</label>
              <div class="input-group">
                <input
                  v-model="state.form.username"
                  type="text"
                  class="form-control"
                  id="username"
                  placeholder="請輸入帳號"
                  required
                />
              </div>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-12">
              <label for="password" class="form-label">密碼</label>
              <div class="input-group">
                <input
                  v-model="state.form.password"
                  type="password"
                  class="form-control"
                  id="password"
                  placeholder="請輸入密碼"
                  required
                />
              </div>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-12">
              <label for="chkPassword" class="form-label">密碼複驗</label>
              <div class="input-group">
                <input
                  type="password"
                  class="form-control"
                  id="chkPassword"
                  placeholder="密碼複驗"
                  required
                />
              </div>
            </div>
          </div>
          <button class="w-100 btn btn-success btn-lg" type="submit">註冊</button>
        </form>
      </div>
    </div>
  </main>
</template>
<style lang="scss" scoped>
.form-label {
  margin-bottom: 0.2rem;
}
</style>
