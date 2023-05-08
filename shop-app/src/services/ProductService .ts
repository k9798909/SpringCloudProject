import type Product from '@/type/dto/ProductDto'
import getHttp from '@/http'
import type ResponseData from '@/type/ResponseData'

class ProductService {
  getAll(): Promise<ResponseData<Product[]>> {
    return getHttp().get('/product-service/product')
  }

  get(id: any): Promise<ResponseData<Product>> {
    return getHttp().get(`/product-service/product/${id}`)
  }
}

export default new ProductService()
