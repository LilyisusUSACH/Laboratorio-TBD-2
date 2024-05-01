<script>
export default {
  data() {
    return {
      items1: ['Opción 1', 'Opción 2', 'Opción 3'],
      items2: ['Sub-Opción 1', 'Sub-Opción 2', 'Sub-Opción 3'],
      items3: ['Opción Final 1', 'Opción Final 2', 'Opción Final 3'],
      columna2Visible: false,
      columna3Visible: false
    }
  },
  methods: {
    mostrarColumna2(item) {
      this.columna2Visible = true
      this.columna3Visible = false
      // Aquí podrías agregar lógica para cargar las opciones de la columna 2 dependiendo del item seleccionado de la columna 1
    },
    mostrarColumna3(item) {
      this.columna3Visible = true
      // Aquí podrías agregar lógica para cargar las opciones de la columna 3 dependiendo del item seleccionado de la columna 2
    }
  }
}
</script>

<template>
  <div class="row">
    <div class="col">
      <div class="card">
        <div class="card-header">
          <h3 class="card-title">Encabezado 1</h3>
        </div>
        <div class="card-body">
          <ul class="list-group">
            <li
              class="list-group-item"
              v-for="(item, index) in items1"
              :key="index"
              @click="mostrarColumna2(item)"
            >
              {{ item }}
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="col" v-if="columna2Visible">
      <div class="card">
        <div class="card-header">
          <h3 class="card-title">Encabezado 2</h3>
        </div>
        <div class="card-body">
          <ul class="list-group">
            <li
              class="list-group-item"
              v-for="(item, index) in items2"
              :key="index"
              @click="mostrarColumna3(item)"
            >
              {{ item }}
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="col" v-if="columna3Visible">
      <div class="card">
        <div class="card-header">
          <h3 class="card-title">Encabezado 3</h3>
        </div>
        <div class="card-body">
          <ul class="list-group">
            <li class="list-group-item" v-for="(item, index) in items3" :key="index">
              {{ item }}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
