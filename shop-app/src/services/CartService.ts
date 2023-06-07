import getApiClient from '@/http'
import usersService from './UsersService'
import type { CartDto } from '@/type/http/dto/CartDto'
import productService from './ProductService'
import type CartProduct from '@/type/domain/CartProduct'
import type ProductDto from '@/type/http/dto/ProductDto'

class CartService {
  async getCartProductList(): Promise<CartProduct[]> {
    let cartDto: CartDto[] = (
      await getApiClient().get('/cart-service/cart/' + usersService.getUsers().username)
    ).data

    let cartProduct: CartProduct[] = []
    cartDto.forEach(async (dto) => {
      let product: ProductDto = (await productService.get(dto.productId)).data
      cartProduct.push({
        productId: dto.productId,
        productName: product.name,
        price: product.price,
        quantity: 1,
        imgUrl: `/api/product-service/product/img/${dto.productId}`
      })
    })
    return cartProduct
  }

  async updateCartProduct(productId: string, quantity: number): Promise<void> {
    let cart: CartDto[] = (
      await getApiClient().get('/cart-service/cart/' + usersService.getUsers().username)
    ).data
    console.log(cart)
    let updCart: CartDto | undefined = cart.find((t) => t.productId == productId)

    if (updCart) {
      updCart.quantity = updCart.quantity + quantity
    } else {
      updCart = {
        username: usersService.getUsers().username,
        productId,
        quantity: quantity
      }
    }

    await getApiClient().post('/cart-service/cart', updCart)
  }
}

const cartService = new CartService()

export default cartService
