import type LoginDto from '@/type/form/LoginForm'
import type LoginResDto from '@/type/dto/LoginResDto'
import type ResponseData from '@/type/http/ResponseData'
import getHttp from '@/http'
import useUsersStore from '@/stores/UseUsersStore'
import type Users from '@/type/stores/Users'

export class UsersService {
  async login(loginDto: LoginDto): Promise<void> {
    let res: ResponseData<LoginResDto> = await getHttp().post('/login', loginDto)
    if (res.data) {
      useUsersStore().login(res.data)
    }
  }

  logout(): void {
    useUsersStore().logout()
  }

  getUsers(): Users | null {
    return useUsersStore().getUsers
  }

  //檢查token是否過期。
  async verifyToken(token: string): Promise<boolean> {
    const res: ResponseData<boolean> = await getHttp().post('/tokenVerify', { token })
    return res.data
  }
}

const usersService = new UsersService()
export default usersService
