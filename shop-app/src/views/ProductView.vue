<script setup lang="ts">
import Product from '../components/Product.vue'
import { reactive, onMounted, watch } from 'vue'
import productService from '@/services/ProductService'
import type ProductDto from '@/type/dto/ProductDto'

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
    console.log(productService.getAll())
    console.log(...(await productService.getAll()).data)
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
    <div class="mx-auto w-50 d-flex">
      <v-text-field
        density="compact"
        variant="solo"
        label="搜尋要找的商品"
        append-inner-icon="mdi-magnify"
        single-line
        hide-details
        v-model="state.searchInput"
        @click:append-inner="(e: MouseEvent) => searchEvent(e)"
      ></v-text-field>

      <router-link to="/cart" custom v-slot="{ navigate }"
        ><v-btn class="mx-2 my-auto" @click="navigate">購物車</v-btn></router-link
      >
    </div>

    <v-parallax
      src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"
      class="my-3 mx-auto"
      max-height="200px"
      width="80%"
    >
      <div class="d-flex flex-column fill-height justify-center align-center text-white">
        <h1 class="text-h4 font-weight-thin mb-4">Vuetify</h1>
        <h4 class="subheading">Build your application today!</h4>
      </div>
    </v-parallax>

    <v-container v-if="state.products.length != 0" min-width="500px">
      <v-row>
        <v-col cols="12" lg="3" md="4" sm="6" v-for="product in state.products">
          <Product class="mx-auto" :product="product"></Product>
        </v-col>
      </v-row>
      <v-pagination :length="1"></v-pagination>
    </v-container>
    
  </main>
</template>
<style lang="scss" scoped></style>
