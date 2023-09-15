<script setup lang="ts">
import cartService from '@/services/CartService'
import { reactive, onMounted } from 'vue'
import type CartProduct from '@/type/dto/CartProductDto'

let cart: CartProduct[] = []
const state = reactive({ cart })

async function loadCartProduct() {
  cartService
    .getCartProductList()
    .then((cartList) => {
      state.cart.push(...cartList)
    })
    .catch((e) => {
      console.error('cartService getCartList error:', e)
    })
}

async function deleteCartProduct(e: MouseEvent, productId: string) {
  await cartService
    .deleteCartProduct(productId)
    .then(() => {
      state.cart = state.cart.filter((t) => t.productId != productId)
    })
    .catch((e) => {
      console.error('cartService deleteCartProduct error:', e)
    })
}

onMounted(loadCartProduct)
</script>

<template>
  <div>
    <v-card border class="mx-auto my-5" width="60%" min-width="400px">
      <v-container>
        <v-row>
          <v-col cols="12">
            <router-link to="/product" custom v-slot="{ navigate }">
              <v-btn @click="navigate" prepend-icon="mdi-arrow-left-bold">繼續購物</v-btn>
            </router-link>
          </v-col>
        </v-row>

        <v-row v-for="dt in state.cart">
          <v-col cols="12">
            <v-card border>
              <div class="d-flex flex-no-wrap justify-space-between">
                <div>
                  <v-card-title class="text-h5"> {{ dt.productName }} </v-card-title>
                  <v-card-subtitle>${{ dt.price }}</v-card-subtitle>
                  <v-card-actions>
                    <v-btn
                      class="ms-2"
                      color="red"
                      variant="tonal"
                      prepend-icon="$close"
                      size="small"
                      @click="deleteCartProduct($event, dt.productId)"
                    >
                      刪除
                    </v-btn>
                  </v-card-actions>
                </div>

                <v-avatar class="mr-3" size="125" rounded="0">
                  <v-img :src="dt.imgUrl"></v-img>
                </v-avatar>
              </div>
            </v-card>
          </v-col>
        </v-row>

        <v-row v-if="state.cart.length > 0">
          <v-col cols="12" class="d-flex flex-row-reverse">
            <router-link to="/" custom v-slot="{ navigate }">
              <v-btn class="mx-0" @click="navigate" append-icon="mdi-arrow-right-bold">結帳</v-btn>
            </router-link>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12">
            <v-alert
              v-if="state.cart.length == 0"
              class="mb-5"
              density="compact"
              type="warning"
              variant="tonal"
              >購物車無商品</v-alert
            >
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<style lang="scss" scoped></style>
