import axios, { type AxiosInstance } from 'axios'
import { ContentTypeEnum } from '../enums/HttpEnum'

const getApiClient = (options = {}): AxiosInstance => {
  return axios.create({
    baseURL: '/api',
    headers: {
      'Content-type': ContentTypeEnum.JSON,
      Authorization: 'Bearer ' + sessionStorage.getItem('token'),
      ...options
    }
  })
}

export default getApiClient
