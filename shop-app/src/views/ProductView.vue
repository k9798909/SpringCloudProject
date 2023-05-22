<script setup lang="ts">
import Product from '../components/Product.vue'
import { reactive, onMounted } from 'vue'
import productService from '@/services/ProductService '
import type ProductDto from '@/type/http/dto/ProductDto'

const allProduct: ProductDto[] = []
const state = reactive({ allProduct })
onMounted(initProductList)

async function initProductList() {
  try {
    state.allProduct.push(...(await productService.getAll()).data)
    state.allProduct.push(...(await productService.getAll()).data)
  } catch (e) {
    console.error('掛載所有商品時發生錯誤', e)
  }
}
</script>

<template>
  <main>
    <div class="product-list">
      <Product :product="product" v-for="product in state.allProduct"></Product>
    </div>
  </main>
</template>
<style lang="scss" scoped>
main {
  .product-list {
    display: grid;
    grid-template-columns: repeat(6, 1fr);
  }
}
</style>
