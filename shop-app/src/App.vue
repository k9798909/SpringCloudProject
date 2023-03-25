<script setup lang="ts">
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
</script>

<template>
  <Header></Header>
  <router-view class="main"></router-view>
  <Footer></Footer>
</template>

<style lang="scss" scoped>
.main {
  min-height: 85vh;
  padding: 2rem;
}
</style>
