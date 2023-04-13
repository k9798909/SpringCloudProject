<script setup lang="ts">
import Product from '../components/Product.vue'
import { reactive, onMounted } from 'vue'
import ProductService from '@/services/ProductService '
import type ProductDto from '@/type/dto/ProductDto'

interface State {
  allProduct: ProductDto[]
}

const state: State = reactive({ allProduct: [] })

const mountedAll = async () => {
  try {
    state.allProduct = (await ProductService.getAll()).data
    console.log(state.allProduct)
  } catch (e) {
    console.error('掛載所有商品時發生錯誤', e)
  }
}

onMounted(mountedAll)
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
    grid-template-columns: repeat(4, 1fr);
  }
}
</style>
