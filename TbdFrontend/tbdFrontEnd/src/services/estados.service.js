import { fetchData } from '@/http-common'

export default {
  async getEstados() {
    return fetchData('estado')
  }
}
