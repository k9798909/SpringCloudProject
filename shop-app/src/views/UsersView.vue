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
    <form>
      <v-card class="mx-auto pa-12 pb-8" width="70%">
        <v-card-title><h2 class="text-center">註冊</h2></v-card-title>
        <div class="text-subtitle-1 text-medium-emphasis">帳號</div>
        <v-text-field
          v-model="state.form.username"
          type="text"
          id="username"
          placeholder="輸入帳號"
          variant="outlined"
          density="compact"
          required
        />
        <div class="text-subtitle-1 text-medium-emphasis">密碼</div>
        <v-text-field
          v-model="state.form.password"
          type="password"
          id="password"
          placeholder="輸入密碼"
          variant="outlined"
          density="compact"
          required
        />
        <div class="text-subtitle-1 text-medium-emphasis">密碼複驗</div>
        <v-text-field
          type="password"
          id="chkPassword"
          placeholder="輸入與上方相同的密碼"
          variant="outlined"
          density="compact"
          required
        />
        <div class="text-subtitle-1 text-medium-emphasis">姓名</div>
        <v-text-field
          v-model="state.form.name"
          type="text"
          id="name"
          placeholder="輸入姓名"
          variant="outlined"
          density="compact"
          required
        />
        <div class="text-subtitle-1 text-medium-emphasis">出生日期</div>
        <v-text-field
          v-model="state.form.birthday"
          type="date"
          id="birthday"
          variant="outlined"
          density="compact"
          required
        />
        <div class="text-subtitle-1 text-medium-emphasis">身分證字號</div>
        <v-text-field
          v-model="state.form.idn"
          type="text"
          id="idn"
          variant="outlined"
          density="compact"
          placeholder="輸入身分證字號"
        />
        <div class="text-subtitle-1 text-medium-emphasis">電子信箱</div>
        <v-text-field
          v-model="state.form.email"
          type="email"
          id="email"
          variant="outlined"
          density="compact"
          placeholder="輸入電子信箱"
        />
        <div class="text-subtitle-1 text-medium-emphasis">地址</div>
        <v-text-field
          v-model="state.form.address"
          type="text"
          id="address"
          variant="outlined"
          density="compact"
          placeholder="輸入地址"
          required
        />

        <v-btn class="w-100 btn btn-success btn-lg" color="indigo" variant="elevated">確認</v-btn>
      </v-card>
    </form>
  </main>
</template>
<style lang="scss" scoped></style>
