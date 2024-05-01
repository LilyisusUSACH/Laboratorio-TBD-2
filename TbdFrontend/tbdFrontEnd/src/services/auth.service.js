import { fetchData, postData } from '@/http-common'

export default {
  async login(data) {
    return postData('login', data)
  },
  async getName() {
    return fetchData('activeUser')
  },
  async register(data) {
    return postData('register', data)
  }
}
