import type Product from '@/types/dto/ProductDto'
import getHttp from '@/http'
import type ResponseData from '@/types/http/ResponseData'

class ProductService {
  findAll(): Promise<ResponseData<Product[]>> {
    return getHttp().get('/product-service/product')
  }

  findBy(id: any): Promise<ResponseData<Product>> {
    return getHttp().get(`/product-service/product/${id}`)
  }
}

const productService = new ProductService()
export default productService
