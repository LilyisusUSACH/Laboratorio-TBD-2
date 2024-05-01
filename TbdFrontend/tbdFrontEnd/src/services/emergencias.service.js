import { fetchData } from '@/http-common'

export default {
  async getEmergencias() {
    return fetchData('emergencia')
  }
}
