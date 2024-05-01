<script setup>
import Table from '@/components/Table.vue'
import NavBar from '@/components/NavBar.vue'
</script>

<template>
  <NavBar></NavBar>
  <Table> </Table>
  <footer class="bg-dark text-light fixed-bottom">
    <div class="container">
      <div class="row">
        <div class="text-md-center">
          <p>Si desea volver a una tabla, seleccione el encabezado</p>
        </div>
      </div>
    </div>
  </footer>
</template>
