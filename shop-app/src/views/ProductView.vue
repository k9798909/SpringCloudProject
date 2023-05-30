<script setup lang="ts">
import Product from '../components/Product.vue'
import { reactive, onMounted, watch } from 'vue'
import productService from '@/services/ProductService'
import type ProductDto from '@/type/http/dto/ProductDto'

const allProduct: ProductDto[] = []
const products: ProductDto[] = []
let searchInput = ''
let searchResult: boolean = true
const state = reactive({ searchInput, products, searchResult })
onMounted(initProductList)

watch(
  () => state.products,
  () => {
    state.searchResult = state.products.length != 0
  },
  { deep: true }
)

async function initProductList() {
  try {
    allProduct.push(...(await productService.getAll()).data)
    allProduct.push(...(await productService.getAll()).data)
    state.products.push(...allProduct)
  } catch (e) {
    console.error('掛載所有商品時發生錯誤', e)
  }
}

function searchEvent(e: MouseEvent) {
  state.products = allProduct.filter((t) => t.name.includes(state.searchInput))
}

</script>

<template>
  <main>
    <form class="my-2">
      <div class="btn-group d-flex justify-content-center align-items-center">
        <div class="d-flex" role="search">
          <input
            v-model="state.searchInput"
            class="form-control form-control-sm me-2"
            type="search"
            placeholder="輸入商品名稱"
            aria-label="Search"
            size="40"
          />
          <button @click="(e) => searchEvent(e)" class="btn btn-sm btn-success" type="button">
            Search
          </button>
        </div>
        <a href="/cart" class="text-body cart-link mx-3"
          ><font-awesome-icon :icon="['fas', 'shopping-cart']" /> 購物車
        </a>
      </div>

      <div class="d-flex w-100 py-2 px-3 align-items-end justify-content-end">
        篩選：
        <select class="form-select form-select-sm mx-2" aria-label="排序條件">
          <option selected>排序條件</option>
          <option value="1">價格</option>
        </select>
        <select class="form-select form-select-sm" aria-label="排序">
          <option selected>順序</option>
          <option value="1">由低到高</option>
          <option value="2">由高到低</option>
        </select>
      </div>
    </form>
    <div v-if="!state.searchResult" class="d-flex justify-content-center w-100 pt-1">
      <div class="alert alert-danger p-2" role="alert">
        <p class="mb-0">
          <font-awesome-icon :icon="['fas', 'file-excel']" size="lg" />
          找不到結果，嘗試不同或更常見的關鍵字。
        </p>
      </div>
    </div>
    <div v-if="state.products.length != 0" class="product-list">
      <Product :product="product" v-for="product in state.products"></Product>
    </div>
  </main>
</template>
<style lang="scss" scoped>
main {
  .product-list {
    display: grid;
    grid-column-gap: 15px;
    grid-template-columns: repeat(auto-fill, 12rem);
    justify-content: space-between;
  }

  .cart-link {
    color: rgba(33, 37, 41), 1 !important;
  }

  select {
    width: 150px;
  }
}

@media (max-width: 900px) {
  main {
    .product-list {
      justify-content: center;
    }
  }
}
</style>
