<script setup>
import authService from '@/services/auth.service'
import { onBeforeMount, ref } from 'vue'

const initials = ref('')

onBeforeMount(() => {
  authService
    .getName()
    .then((response) => {
      initials.value = response.activeUser.substring(0, 3).toUpperCase()
    })
    .catch(() => {
      initials.value = ''
    })
})
</script>

<template>
  <nav class="navbar fixed-top">
    <div class="container-fluid justify-content-around">
      <div />
      <span class="text">Mi Aplicación</span>
      <div class="profile-circle">
        <span class="initials">{{ initials }}</span>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  background-color: rgba(51, 51, 51, 0.5);
}
.profile-circle {
  width: 45px;
  height: 45px;
  border-radius: 50%;
  background-color: #243346;
  overflow: hidden;
  display: flex;
  right: 20px;
  justify-content: center;
  align-items: center;
  margin-left: 10px;
  box-shadow: 0 2px 4px 1px rgba(0, 0, 0, 0.5);
}
.text {
  color: azure;
  font-size: 20px;
  font-weight: bold;
  margin-left: 10px;
  display: flex;
  align-items: center;
}
.initials {
  color: white; /* Cambia el color del texto según sea necesario */
  font-size: 16px; /* Ajusta el tamaño de la fuente según sea necesario */
  font-weight: bold; /* Ajusta el peso de la fuente según sea necesario */
}
</style>
