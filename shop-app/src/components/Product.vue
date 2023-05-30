<script setup lang="ts">
import cartService from '@/services/CartService'
import type ProductDto from '@/type/http/dto/ProductDto'
export interface Prop {
  product: ProductDto
}
const props = defineProps<Prop>()
const imgUrl = `/api/product-service/product/img/${props.product.id}`

async function addCardProduct() {
  try {
    await cartService.updateCartProduct(props.product.id, 1)
    alert('加入購物車成功')
  } catch (e) {
    console.error('addCardProduct error', e)
  }
}
</script>
<template>
  <div class="card mb-4 shadow-sm product-card">
    <img class="bd-placeholder-img card-img-top" :src="imgUrl" />
    <div class="card-body">
      <div class="card-text">
        <p class="card-text-title">商品名稱：{{ props.product.name }}</p>
        <p class="card-text-content">
          {{ props.product.description }}
        </p>
      </div>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <button type="button" class="btn btn-sm btn-success border border-light">明細</button>
          <button @click="addCardProduct" type="button" class="btn btn-sm btn-success border border-light">
            加入購物車
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.card-img-top {
  width: '100%';
  height: 50%;
}

.card-body {
  height: 50%;
  padding: 0.5rem 0.5rem;
}

.product-card {
  width: 12rem;
  height: 20rem;
}

.card-text {
  min-height: 65%;
}

.card-text-title {
  font-weight: bold;
  margin-bottom: 2px;
}

.card-text-content {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  text-overflow: ellipsis;
  overflow: hidden;
}
</style>
