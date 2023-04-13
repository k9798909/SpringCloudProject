import type Product from '@/type/dto/ProductDto'
import http from '@/http/http-common'
import type ResponseData from '@/type/ResponseData'

class ProductService {
  getAll(): Promise<ResponseData<Product[]>> {
    return http.get('/product')
  }

  get(id: any): Promise<ResponseData<Product>> {
    return http.get(`/product/${id}`)
  }
}

export default new ProductService()
