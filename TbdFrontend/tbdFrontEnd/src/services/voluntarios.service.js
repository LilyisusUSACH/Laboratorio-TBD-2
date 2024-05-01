import { postData } from '@/http-common'

export default {
  async postVoluntario(data) {
    return postData('voluntario', data)
  }
}
