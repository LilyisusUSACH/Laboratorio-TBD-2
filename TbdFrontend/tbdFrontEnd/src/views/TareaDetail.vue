<script setup>
import NavBar from '@/components/NavBar.vue'
import Multiselect from '@vueform/multiselect'
import { useRoute } from 'vue-router'
import { ref, onBeforeMount, onMounted } from 'vue'
import tareasService from '@/services/tareas.service'
import estadosService from '@/services/estados.service'
import habilidadesService from '@/services/habilidades.service'
import emergenciasService from '@/services/emergencias.service'
import habtareaService from '@/services/habtarea.service'

const route = useRoute()
const id = ref(route.params.id)
const estados = ref([])

const editing = ref(false)

const allEmergencias = ref([])

const allHabilidades = ref([])
const habilidades = ref([])

onBeforeMount(() => {
  tareasService.getTareaById(id.value).then((response) => {
    tarea.value = response
    habilidadesService.getHabilidadesByEmergency(response.id_emergencia).then((response) => {
      allHabilidades.value = response
      allHabilidades.value.map((hability) => {
        hability.value = hability.id
        hability.label = hability.descripcion
      })
      //console.log(allHabilidades.value)
    })
  })
  emergenciasService.getEmergencias().then((response) => {
    allEmergencias.value = response
    allEmergencias.value.map((emergency) => {
      emergency.value = emergency.id
      emergency.label = emergency.descripcion
    })
  })
  estadosService.getEstados().then((response) => {
    estados.value = response
    estados.value.map((state) => {
      state.value = state.id
      state.label = state.descripcion
    })
    console.log(estados.value)
  })
})

onMounted(() => {
  tareasService.getHabilidadesByTarea(id.value).then((response) => {
    response.map((hability) => {
      habilidades.value.push(hability.id)
    })
  })
  console.log(tarea.value)
})
const tarea = ref({
  id: '',
  descripcion: '',
  id_emergencia: '',
  id_estado: ''
})

const onEdit = () => {
  if (!editing.value) document.getElementById('editButton').style.display = 'none'
  else document.getElementById('editButton').style.display = ''
  editing.value = !editing.value
}

const onPut = () => {
  // actualizar
  habtareaService.deleteHabTarea(tarea.value.id)
  tareasService.putHabilidadUpdate(tarea.value)
  if (habilidades.value.length > 0)
    tareasService.addHabilidades(tarea.value.id, { id_habilidades: habilidades.value.toString() })
  onEdit()
}
</script>

<template>
  <NavBar></NavBar>
  <div class="container paper">
    <div class="row d-flex justify-content-center">
      <div class="col-12 align-content-center title h4">Detalle de tareas</div>
      <div class="title2" id="editButton" @click="onEdit">
        <svg
          style="display: relative; transform: translatex(-25%) translateY(7%)"
          xmlns="http://www.w3.org/2000/svg"
          width="35"
          height="35"
          fill="currentColor"
          class="bi bi-pencil-square"
          viewBox="0 0 16 16"
        >
          <path
            d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"
          />
          <path
            fill-rule="evenodd"
            d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"
          />
        </svg>
      </div>
    </div>
    <div class="container d-grid tarea align-content-around pb-5">
      <div class="col">
        <label for="name" class="">Descripcion de la tarea:</label>
        <input
          type="text"
          class="form-control custom-shadow"
          id="name"
          :disabled="!editing"
          v-model="tarea.descripcion"
        />
      </div>
      <div>
        <label for="name" class="col">Seleccione la emergencia</label>
        <Multiselect
          :disabled="!editing"
          v-model="tarea.id_emergencia"
          :searchable="true"
          :loading="false"
          :canClear="false"
          class="multiselect-red"
          :options="allEmergencias"
        />
      </div>
      <div>
        <label for="name" class="col">Seleccione las habilidades asociadas</label>
        <Multiselect
          :disabled="!editing"
          v-model="habilidades"
          mode="tags"
          class="multiselect-red"
          :close-on-select="false"
          :searchable="true"
          :create-option="false"
          :options="allHabilidades"
        />
      </div>
      <div>
        <label for="name" class="col">Seleccione el estado de la tarea</label>
        <Multiselect
          :disabled="!editing"
          v-model="tarea.id_estado"
          :searchable="true"
          :loading="false"
          :canClear="false"
          class="multiselect-red"
          track-by="id"
          :options="estados"
        />
      </div>
      <div class="row" v-if="editing">
        <button
          type="submit"
          @click="onPut"
          class="btn submit btn-primary mt-3 mx-auto"
          style="width: 96%"
        >
          Editar Tarea
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.form-control.custom-shadow {
  padding-block: 8px;
}
.form-control.custom-shadow:focus {
  outline: 0;
  border-color: lightgray;
  box-shadow: 0 0 0 0.25rem rgba(199, 50, 60, 0.25); /* Ancho del anillo y opacidad */
}
.multiselect-red {
  --ms-option-bg-selected: rgba(199, 50, 60, 1);
  --ms-option-bg-selected-pointed: rgb(180, 40, 48);
  --ms-tag-bg: rgba(199, 50, 60, 1);
  --ms-ring-color: rgba(199, 50, 60, 0.25);
}
.paper {
  overflow: visible;
  background-color: rgba(255, 255, 255, 1);
  width: 40vw;
  margin-top: 10vh;
  min-height: 60vh;
  border-radius: 25px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
}
.title {
  width: 50%;
  height: 50px;
  margin-top: -5%;
  margin-left: -40%;
  background-color: #243346;
  color: white;
  text-align: center;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
}

.title2 {
  position: absolute;
  transform: translateY(-25%);
  right: 31.5%;
  width: 40px;
  height: 40px;
  background-color: #1596a4;
  color: white;
  text-align: center;
  border-radius: 10px;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.3);
}

.title2:hover {
  filter: brightness(120%);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
  cursor: pointer;
}

.container.tarea {
  height: 100%;
}

.submit {
  background-color: #243346;
  border-color: #243346;
  &:hover {
    filter: brightness(150%);
  }
}
</style>
