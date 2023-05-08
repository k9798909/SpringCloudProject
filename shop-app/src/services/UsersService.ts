import type LoginDto from '@/type/dto/LoginDto'
import type LoginResDto from '@/type/dto/LoginResDto'
import getHttp from '@/http'
import type ResponseData from '@/type/ResponseData'

class UsersService {
  async login(loginDto: LoginDto) {
    let res: ResponseData<LoginResDto> = await getHttp().post('/login', loginDto)
    sessionStorage.setItem('token', res.data.token)
    sessionStorage.setItem('name', res.data.name)
  }

  getName(): string | null {
    return sessionStorage.getItem('name')
  }

  isLogin(): boolean {
    return !!sessionStorage.getItem('token')
  }

  logout() {
    sessionStorage.removeItem('token')
  }
}

export default new UsersService()
