<script setup lang="ts">
import { reactive } from 'vue'

interface Ilink {
  name: string
  href: string
}

let linkArray: Ilink[] = [
  {
    name: 'Tour',
    href: '#'
  },
  {
    name: '商品',
    href: '/product'
  },
  {
    name: 'Features',
    href: '#'
  },
  {
    name: 'Enterprise',
    href: '#'
  },
  {
    name: 'Support',
    href: '#'
  },
  {
    name: 'Pricing',
    href: '#'
  }
]

let isLogin: Boolean = false

const state = reactive({ isLogin: isLogin })

const funcLogin = (): void => {
  state.isLogin = !state.isLogin
}
</script>

<template>
  <header>
    <div class="logo-area">
      <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />
    </div>
    <nav>
      <div class="link-bar">
        <a v-for="link in linkArray" :href="link.href">{{ link.name }}</a>
      </div>
    </nav>
    <div class="login-area" v-if="!state.isLogin">
      <button href="#" v-on:click="funcLogin()">登入</button>
    </div>
    <div class="login-area" v-if="state.isLogin">
      你好 無測試<span> </span><button href="#" v-on:click="funcLogin()">登出</button>
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
  padding: 0.5rem 3rem;

  a {
    color: $font-color;
    transition: ease-in-out color 0.15s;
    padding: 0.3rem;
    font-size: 1.2rem;
  }

  a:hover {
    color: $hover-color;
  }

  .logo-area {
    width: 15%;
    .logo {
      display: block;
      width: 35px;
      height: 35px;
      padding-top: 0.3rem;
    }
  }

  .login-area {
    width: 15%;
    padding: 0.3rem;
    color: $font-color;
    font-size: 1.2rem;
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
    text-align: center;
    display: flex;
    justify-content: flex-start;
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
