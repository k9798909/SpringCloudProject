import type LoginDto from '@/types/form/LoginForm'
import type LoginResDto from '@/types/dto/LoginResDto'
import type ResponseData from '@/types/http/ResponseData'
import getHttp from '@/http'
import useUsersStore from '@/stores/UseUsersStore'
import type Users from '@/types/stores/Users'
import type SignUpForm  from '@/types/form/SignUpForm'

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

  async signUp(form: SignUpForm): Promise<void> {
    await getHttp().post('public/users/signUp', form)
  }

  async checkUsername(username: string): Promise<boolean> {
    return (await getHttp().get('public/users/checkUsername', { params: { username } })).data
  }
}

const usersService = new UsersService()
export default usersService
