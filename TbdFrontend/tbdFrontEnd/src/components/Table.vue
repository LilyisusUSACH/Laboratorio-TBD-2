<script>
export default {
  data() {
    return {
      tareas: [],
      voluntarios: [],
      columna2Visible: false,
      columna3Visible: false,
      itemSeleccionado1: null,
      itemSeleccionado2: null,
      itemSeleccionado3: null
    }
  },
  methods: {
    seleccionarItem(columna, index, item) {
      if (columna === 1) {
        this.itemSeleccionado1 = index
        this.columna2Visible = true
        this.itemSeleccionado2 = null
        this.columna3Visible = false
        tareasService.getTareasByEmergencia(item.id).then((response) => {
          this.tareas = response
          this.tareas.forEach((tarea) => {
            rankingService.counVolTarea(tarea.id).then((response) => (tarea.cantidad = response))
          })
          console.log(this.tareas)
        })
      } else if (columna === 2) {
        this.itemSeleccionado2 = index
        this.columna3Visible = true
        this.itemSeleccionado3 = null
        console.log(item)

        rankingService.volsTarea(item.id).then((response) => {
          this.voluntarios = response
          console.log(this.voluntarios)
        })
      } else {
        this.itemSeleccionado3 = index
      }
    },
    handleBack(columna) {
      if (columna === 1) {
        this.columna2Visible = false
        this.columna3Visible = false
        this.itemSeleccionado1 = null
        this.tareas = null
      } else if (columna === 2) {
        this.columna2Visible = true
        this.columna3Visible = false
        this.itemSeleccionado2 = null
        this.voluntarios = null
      }
    }
  }
}
</script>

<script setup>
import emergenciasService from '@/services/emergencias.service'
import estadosService from '@/services/estados.service'
import rankingService from '@/services/ranking.service'
import tareasService from '@/services/tareas.service'
import { onBeforeMount, ref } from 'vue'
const emergencias = ref([])
const allEstados = ref([])

onBeforeMount(() => {
  emergenciasService
    .getEmergencias()
    .then((response) => {
      emergencias.value = response
    })
    .catch((error) => console.log('EL ERROR ES ' + error))

  estadosService
    .getEstados()
    .then((response) => {
      allEstados.value = response
    })
    .catch((error) => console.log('EL ERROR ES ' + error))
})

const getEstadoDesc = (id) => {
  return allEstados.value.find((estado) => estado.id === id).descripcion
}

async function getVolByTarea(tarea_id) {
  return await rankingService.counVolTarea(tarea_id).then((response) => response)
}
</script>

<template>
  <div class="row">
    <div class="col" :style="{ maxWidth: columna2Visible ? '400px' : '60vw' }">
      <div class="card">
        <div class="card-header">
          <h3 class="card-title" @click="handleBack(1)">Emergencias</h3>
        </div>
        <div class="card-body mb-4 mt-2">
          <table class="table table-bordered">
            <thead class="sticky-top bg-white">
              <tr class>
                <th class="idCol">ID</th>
                <th class="idName">Nombre</th>
                <th>Descripción</th>
                <th v-if="!columna2Visible" class="idEstado">Estado</th>
              </tr>
            </thead>
            <tbody class="scroll">
              <tr
                v-for="(item, index) in emergencias"
                :key="index"
                @click="seleccionarItem(1, index, item)"
                :class="{ 'table-primary': itemSeleccionado1 === index }"
              >
                <th class="idCol">{{ item.id }}</th>
                <td class="idName">{{ item.nombre }}</td>
                <td>{{ item.descripcion }}</td>
                <td v-if="!columna2Visible" class="idEstado">
                  {{ getEstadoDesc(item.id_estado) }}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <transition name="bounce">
      <div
        class="col"
        v-if="columna2Visible"
        :style="{ maxWidth: columna3Visible ? '300px' : '50vw' }"
      >
        <div class="card">
          <div class="card-header">
            <h3 class="card-title" @click="handleBack(2)">Tareas</h3>
          </div>
          <div class="card-body">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th class="idCol">ID</th>
                  <th>Nombre</th>
                  <th v-if="!columna3Visible" class="idCantidad">Cantidad voluntarios</th>
                  <th v-if="!columna3Visible" class="idEstado">Estado</th>
                </tr>
              </thead>
              <tbody class="scroll">
                <tr
                  v-for="(tarea, index) in tareas"
                  :key="index"
                  @click="seleccionarItem(2, index, tarea)"
                  :class="{ 'table-primary': itemSeleccionado2 === index }"
                >
                  <th class="idCol" scope="row">{{ tarea.id }}</th>
                  <td>{{ tarea.descripcion }}</td>
                  <td v-if="!columna3Visible" class="idCantidad">
                    {{ tarea.cantidad }}
                  </td>
                  <td v-if="!columna3Visible" class="idEstado">
                    {{ getEstadoDesc(tarea.id_estado) }}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </transition>
    <transition name="bounce">
      <div class="col a" v-if="columna3Visible">
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">Voluntarios</h3>
          </div>
          <div class="card-body">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>RUT</th>
                  <th>Nombre</th>
                  <th>Participa</th>
                </tr>
              </thead>
              <tbody class="scroll">
                <tr
                  v-for="(item, index) in voluntarios"
                  :key="index"
                  @click="seleccionarItem(3, index)"
                >
                  <th scope="row">{{ item.rut }}</th>
                  <td>{{ item.nombre }}</td>
                  <td>{{ item.participa }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<style scoped>
.idCol {
  width: 40px;
  text-align: end;
}
.idName {
  width: 30%;
  text-align: center;
}
.idEstado {
  width: 15%;
  text-align: center;
}
.idCantidad {
  width: 20%;
  text-align: center;
}

.card {
  z-index: 0;
  margin: 'auto';
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

.col {
  max-width: 30rem;
}

.scroll {
  max-height: 35vh;
  overflow-y: auto;
  display: block;
}

.card-header {
  background-color: #243346;
  text-align: center;
  color: white;
}

tr {
  display: table;
  width: 100%;
  table-layout: fixed;
}

.bounce-enter-active {
  animation: bounce-in 0.5s;
}
.bounce-leave-active {
  animation: bounce-in 0.5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  75% {
    transform: scale(1.3);
  }
  100% {
    transform: scale(1);
  }
}
</style>
