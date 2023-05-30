<script setup lang="ts"></script>

<template>
  <div class="cart w-75 mx-auto">
    <div class="cart-title">
      <h5>
        <a href="/product" class="text-body"
          ><font-awesome-icon :icon="['fas', 'arrow-left']" /> 繼續購物</a
        >
      </h5>
    </div>

    <div class="cart-item d-flex justify-content-between">
      <div class="d-flex flex-row align-items-center">
        <div>
          <img
            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img1.webp"
            class="product-img"
          />
        </div>

        <div class="ms-3">
          <h5>Iphone 11 pro</h5>
          <p class="mb-0">256GB, Navy Blue</p>
        </div>
      </div>

      <div class="d-flex flex-row align-items-center">
        <div class="qty">2</div>
        <div class="price">$900</div>
        <a href="#" style="color: #1e3054"><font-awesome-icon :icon="['fas', 'trash']" /></a>
      </div>
    </div>

    <div class="cart-item d-flex justify-content-between">
      <div class="d-flex flex-row align-items-center">
        <div>
          <img
            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img1.webp"
            class="product-img"
          />
        </div>

        <div class="ms-3">
          <h5>Iphone 11 pro</h5>
          <p class="mb-0">256GB, Navy Blue</p>
        </div>
      </div>

      <div class="d-flex flex-row align-items-center">
        <div class="qty">2</div>
        <div class="price">$900</div>
        <a href="#" style="color: #1e3054"><font-awesome-icon :icon="['fas', 'trash']" /></a>
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

  .qty,.price {
    font-size: 1.5rem;
    margin-right: 3rem;
  }

  .product-img {
    border-radius: 0.5rem;
    max-width: 100%;
    height: auto;
    width: 65px;
  }

  .cart-item {
    padding: 0.8rem;
    border-bottom: 1px solid rgba(0, 0, 0, 0.175);
  }

  .cart-footer {
    padding-top: 1rem;
    text-align: right;
  }
}
</style>
