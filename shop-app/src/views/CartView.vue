<script setup lang="ts">
import cartService from '@/services/CartService'
import { reactive, onMounted } from 'vue'
import type CartProduct from '@/type/domain/CartProduct'

let cart: CartProduct[] = []
const state = reactive({ cart })
onMounted(init)

async function init() {
  try {
    state.cart.push(...(await cartService.getCartProductList()))
  } catch (error) {
    console.error('cartService getCartList error:', error)
  }
}

async function deleteCartProduct(e: MouseEvent, productId: string) {
  try {
    await cartService.deleteCartProduct(productId)
    state.cart = state.cart.filter((t) => t.productId != productId)
  } catch (error) {
    console.error('cartService deleteCartProduct error:', error)
  }
}
</script>

<template>
  <div class="cart w-75 mx-auto">
    <div class="cart-title">
      <h5>
        <a href="/product" class="text-body"
          ><font-awesome-icon :icon="['fas', 'arrow-left']" /> 繼續購物</a
        >
      </h5>
    </div>

    <div v-for="dt in state.cart" class="cart-item d-flex justify-content-between">
      <div class="d-flex flex-row align-items-center">
        <div>
          <img class="product-img" :src="dt.imgUrl" />
        </div>

        <div class="ms-3">
          <h5>{{ dt.productName }}</h5>
          <p class="mb-0">產品介紹</p>
        </div>
      </div>

      <div class="d-flex flex-row align-items-center">
        <div class="qty">{{ dt.quantity }}</div>
        <div class="price">{{ dt.price }}</div>
        <button class="trashBtn" @click="deleteCartProduct($event, dt.productId)">
          <font-awesome-icon :icon="['fas', 'trash']" />
        </button>
      </div>
    </div>

    <div class="cart-footer">
      <h5>
        <a href="#" class="text-body">結帳 <font-awesome-icon :icon="['fas', 'arrow-right']" /></a>
      </h5>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.cart {
  display: flex;
  flex-direction: column;
  word-wrap: break-word;
  border-radius: 0.375rem;
  padding: 1.5rem;
  margin-left: auto;
  margin-right: auto;

  .cart-title {
    border-bottom: 1px solid rgba(0, 0, 0, 0.175);
  }

  .qty,
  .price {
    font-size: 1.5rem;
    margin-right: 3rem;
  }

  .product-img {
    border-radius: 0.5rem;
    max-width: 100%;
    height: 70px;
    width: 70px;
  }

  .cart-item {
    padding: 0.8rem;
    border-bottom: 1px solid rgba(0, 0, 0, 0.175);
  }

  .cart-footer {
    padding-top: 1rem;
    text-align: right;
  }

  .trashBtn {
    background-color: transparent;
    border: none;
    color: #1e3054;
    text-decoration: none;
    cursor: pointer;
  }
}
</style>
