import axios, { type AxiosInstance } from 'axios'
import { ContentTypeEnum } from '../enums/httpEnum'

const apiClient: AxiosInstance = axios.create({
  baseURL: '/api',
  headers: {
    'Content-type': ContentTypeEnum.JSON
  }
})

export default apiClient
