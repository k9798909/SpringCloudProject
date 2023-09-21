import axios, { type AxiosInstance } from 'axios'
import { ContentTypeEnum } from '../common/HttpEnum'
import usersService from '@/services/UsersService'

const getApiClient = (options = {}): AxiosInstance => {
  return axios.create({
    baseURL: '/api',
    headers: {
      'Content-type': ContentTypeEnum.JSON,
      Authorization: 'Bearer ' + usersService.getUsers()?.token || '',
      ...options
    }
  })
}

export default getApiClient
