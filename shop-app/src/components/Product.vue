<script setup lang="ts">
import type ProductDto from '@/type/dto/ProductDto'
export interface Prop {
  product: ProductDto
}
const props = defineProps<Prop>()
const imgUrl = `/api/product-service/product/img/${props.product.id}`
</script>
<template>
  <div class="card mb-4 shadow-sm product-card">
    <img class="bd-placeholder-img card-img-top" width="100%" height="225" :src="imgUrl" />
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
          <button type="button" class="btn btn-sm btn-success border border-light">
            加入購物車
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.product-card {
  width: 16rem;
  height: 25rem;
}

.card-text {
  min-height: 70%;
}

.card-text-title {
  font-weight: bold;
}

.card-text-content {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  text-overflow: ellipsis;
  overflow: hidden;
}
</style>
