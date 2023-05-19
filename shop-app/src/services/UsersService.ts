import type LoginDto from '@/type/dto/LoginDto'
import type LoginResDto from '@/type/dto/LoginResDto'
import getHttp from '@/http'
import type ResponseData from '@/type/ResponseData'
import useUsersStore from '@/stores/UseUsersStore'

const usersStore = useUsersStore();

class UsersService {
  async login(loginDto: LoginDto) {
    let res: ResponseData<LoginResDto> = await getHttp().post('/login', loginDto)
    usersStore.login(res.data);
  }

  getName(): string | null {
    return usersStore.getName()?''
  }

  isLogin(): boolean {
    return !!sessionStorage.getItem('token')
  }

  logout() {
    sessionStorage.removeItem('token')
  }
}

export default new UsersService()
