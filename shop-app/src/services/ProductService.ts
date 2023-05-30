import type Product from '@/type/http/dto/ProductDto'
import getHttp from '@/http'
import type ResponseData from '@/type/http/ResponseData'

class ProductService {
  getAll(): Promise<ResponseData<Product[]>> {
    return getHttp().get('/product-service/product')
  }

  get(id: any): Promise<ResponseData<Product>> {
    return getHttp().get(`/product-service/product/${id}`)
  }
}

const productService = new ProductService()
export default productService
