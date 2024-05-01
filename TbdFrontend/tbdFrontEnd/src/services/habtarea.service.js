import { deleteData } from '@/http-common'

//tarea_habilidad/byTarea?id_tarea=1
export default {
  async deleteHabTarea(id) {
    return deleteData(`tarea_habilidad/byTarea?id_tarea=${id}`)
  }
}
