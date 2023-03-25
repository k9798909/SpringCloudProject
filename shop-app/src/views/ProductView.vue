<script setup lang="ts">
import Product from '../components/Product.vue'
</script>

<template>
  <main>
    <div class="product-list">
      <Product v-for="item in 10"></Product>
    </div>
  </main>
</template>
<style lang="scss" scoped>
main {
  .product-list {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
  }


}
</style>
