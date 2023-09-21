import getApiClient from '@/http'
import usersService from './UsersService'
import type { CartDto } from '@/types/dto/CartDto'
import productService from './ProductService'
import type CartProduct from '@/types/dto/CartProductDto'
import type ProductDto from '@/types/dto/ProductDto'
import NotLoginError from '@/common/NotLoginError'

class CartService {
  async getCartProductList(): Promise<CartProduct[]> {
    const users = usersService.getUsers()
    if (!users) {
      throw new NotLoginError('未登入')
    }

    let cartProduct: CartProduct[] = []
    let cartDto: CartDto[] = (await getApiClient().get(`/cart-service/cart/${users.username}`)).data
    for (let dto of cartDto) {
      let product: ProductDto = (await productService.findBy(dto.productId)).data
      cartProduct.push({
        productId: dto.productId,
        productName: product.name,
        price: product.price,
        quantity: 1,
        imgUrl: `/api/product-service/product/img/${dto.productId}`
      })
    }

    return cartProduct
  }

  async updateCartProduct(productId: string, quantity: number): Promise<void> {
    const users = usersService.getUsers()
    if (!users) {
      throw new NotLoginError('未登入')
    }

    let cart: CartDto[] = (await getApiClient().get('/cart-service/cart/' + users.username)).data
    let updCart: CartDto | undefined = cart.find((t) => t.productId == productId)
    if (updCart) {
      updCart.quantity = updCart.quantity + quantity
    } else {
      updCart = {
        username: users.username,
        productId,
        quantity: quantity
      }
    }

    await getApiClient().post('/cart-service/cart', updCart)
  }

  async deleteCartProduct(productId: String): Promise<void> {
    const users = usersService.getUsers()
    if (!users) {
      throw new NotLoginError('未登入')
    }

    await getApiClient().delete(`/cart-service/cart/${users.username}/${productId}`)
  }
}

const cartService = new CartService()
export default cartService
