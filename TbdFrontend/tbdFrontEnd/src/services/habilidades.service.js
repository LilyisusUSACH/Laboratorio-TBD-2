import { fetchData } from '@/http-common'

export default {
  async getHabilidades() {
    return fetchData('habilidad')
  },
  async getHabilidadesByEmergency(id) {
    return fetchData(`habilidad/byEmergency?id=${id}`)
  }
}
