import { fetchData } from '@/http-common'

export default {
  async getGeoJsonByEmergency(id) {
    return fetchData(`geo/volByEme?id_emergencia=${id}`)
  }
}
