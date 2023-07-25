import type LoginDto from '@/type/form/LoginForm'
import type LoginResDto from '@/type/dto/LoginResDto'
import type ResponseData from '@/type/http/ResponseData'
import getHttp from '@/http'
import useUsersStore from '@/stores/UseUsersStore'
import type Users from '@/type/stores/Users'

export class UsersService {
  async login(loginDto: LoginDto): Promise<void> {
    let res: ResponseData<LoginResDto> = await getHttp().post('/login', loginDto)
    useUsersStore().login(res.data)
  }

  getUsers(): Users {
    return useUsersStore().getUsers
  }

  //檢查store裡的user，token是否還生效，若已過期會刪除
  async verifyStoreUsersToken(): Promise<boolean> {
    const token = useUsersStore().getUsers.token
    if (!token) {
      return false
    }
    
    const res: ResponseData<boolean> = await getHttp().post('/tokenVerify', { token: token })
    const isVerify = res.data
    return isVerify
  }

  logout(): void {
    useUsersStore().logout()
  }
}

const usersService = new UsersService()
export default usersService
