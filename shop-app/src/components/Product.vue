<script setup lang="ts"></script>
<template>
  <div class="card mb-4 shadow-sm product-card">
    <svg
      class="bd-placeholder-img card-img-top"
      width="100%"
      height="225"
      xmlns="http://www.w3.org/2000/svg"
      role="img"
      aria-label="Placeholder: Thumbnail"
      preserveAspectRatio="xMidYMid slice"
      focusable="false"
    >
      <rect width="100%" height="100%" fill="#55595c" />
    </svg>

    <div class="card-body">
      <p class="card-text">
        This is a wider card with supporting text below as a natural lead-in to additional content.
        This content is a little bit longer.
      </p>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <button type="button" class="btn btn-sm btn-success border border-light">明細</button>
          <button type="button" class="btn btn-sm btn-success border border-light">加入購物車</button>
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
</style>
