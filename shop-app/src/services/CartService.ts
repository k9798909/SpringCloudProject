import getApiClient from '@/http'
import usersService from './UsersService'
import type { CartDto } from '@/type/http/dto/CartDto'
import type ProductDto from '@/type/http/dto/ProductDto'

class CartService {
  getCartList(): void {
    getApiClient()
      .get('/cart-service/cart' + usersService.getUsers().username)
      .then((res) => console.log(res.data))
      .catch((err) => console.log('asd', err))
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
