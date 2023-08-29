<script setup lang="ts">
import NotLoginError from '@/data/NotLoginError'
import cartService from '@/services/CartService'
import type ProductDto from '@/type/dto/ProductDto'
import { useRouter } from 'vue-router'
export interface Prop {
  product: ProductDto
}

const router = useRouter()
const props = defineProps<Prop>()
const imgUrl = `/api/product-service/product/img/${props.product.id}`

async function addCardProduct() {
  cartService.updateCartProduct(props.product.id, 1)
    .then(() => {
      alert('加入購物車成功')
    })
    .catch(e => {
      if (e instanceof NotLoginError) {
        router.push('/login')
        return
      }
      console.error('addCardProduct error', e)
    })
}
</script>
<template>
  <v-card width="230px">
    <v-img :src="imgUrl" height="200px" cover></v-img>
    <v-card-title>
      <b>商品名稱：{{ props.product.name }}</b>
    </v-card-title>
    <v-card-subtitle> 售價:{{ props.product.price }} </v-card-subtitle>
    <v-card-text>
      <div class="card-text-content">
        {{ props.product.description }}
      </div>
    </v-card-text>
    <v-card-actions>
      <v-btn color="indigo" variant="elevated" @click="addCardProduct"> 加入購物車 </v-btn>
      <v-btn color="indigo" variant="elevated"> 明細 </v-btn>
    </v-card-actions>
  </v-card>
</template>

<style lang="scss" scoped>
.card-text-content {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  text-overflow: ellipsis;
  overflow: hidden;
}
</style>
