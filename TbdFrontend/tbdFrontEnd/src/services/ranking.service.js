import { fetchData } from '@/http-common'

export default {
  async counVolTarea(id) {
    return fetchData(`ranking/voltarea?id_tarea=${id}`)
  },
  async volsTarea(id) {
    return fetchData(`ranking/volstarea?id_tarea=${id}`)
  }
}
