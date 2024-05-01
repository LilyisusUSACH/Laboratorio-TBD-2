<script setup>
import NavBar from '@/components/NavBar.vue'
import router from '@/router'
import emergenciasService from '@/services/emergencias.service'
import habilidadesService from '@/services/habilidades.service'
import tareasService from '@/services/tareas.service'
import Multiselect from '@vueform/multiselect'
import { onBeforeMount, ref } from 'vue'

let data = ref({
  nombre: '',
  emergencia: '',
  habilidades: []
})

const allEmergencias = ref([])

const posiblesHabilities = ref([])

const optionsHabilities = () => {
  habilidadesService.getHabilidadesByEmergency(data.value.emergencia).then((response) => {
    posiblesHabilities.value = response
    posiblesHabilities.value.map((hability) => {
      hability.value = hability.id
      hability.label = hability.descripcion
    })
    data.value.habilidades = data.value.habilidades.filter((hability) =>
      posiblesHabilities.value.some((posHability) => posHability.id == hability)
    )
  })
}
onBeforeMount(() => {
  emergenciasService.getEmergencias().then((response) => {
    allEmergencias.value = response
    allEmergencias.value.map((emergency) => {
      emergency.value = emergency.id
      emergency.label = emergency.descripcion
    })
  })
})

const onPost = () => {
  const tarea = {
    id_estado: 2,
    descripcion: data.value.nombre,
    id_emergencia: data.value.emergencia
  }
  tareasService.postTarea(tarea).then((response) => {
    console.log(data.value.habilidades)
    if (data.value.habilidades.length > 0) {
      tareasService
        .addHabilidades(response.id, {
          id_habilidades: data.value.habilidades.toString()
        })
        .then(() => {
          router.push('' + response.id)
        })
    } else {
      router.push('' + response.id)
    }
  })
}
const submitForm = () => {
  console.log('Form submitted:', data.value)
}
</script>

<template>
  <NavBar></NavBar>
  <div class="container paper">
    <div class="row d-flex justify-content-center">
      <div class="col-12 align-content-center title h4">Ingreso de tareas</div>
    </div>
    <div class="container d-grid tarea align-content-around pb-5">
      <div class="mb-3 col">
        <label for="name" class="">Descripcion de la tarea:</label>
        <input type="text" class="form-control custom-shadow" id="name" v-model="data.nombre" />
      </div>
      <div>
        <label for="name" class="col">Seleccione la emergencia</label>
        <Multiselect
          v-model="data.emergencia"
          ref="multiselect"
          @select="optionsHabilities"
          :searchable="true"
          :loading="false"
          :canClear="false"
          class="multiselect-red"
          :options="allEmergencias"
        />
      </div>
      <div>
        <label for="name" class="mt-3 col">Seleccione las habilidades asociadas</label>
        <Multiselect
          v-model="data.habilidades"
          mode="tags"
          class="multiselect-red"
          :close-on-select="false"
          :searchable="true"
          :create-option="false"
          :options="posiblesHabilities"
        />
      </div>
      <div class="row">
        <button type="submit" @click="onPost" class="btn submit btn-primary mt-3">
          Crear tarea
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
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
  height: 60vh;
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
