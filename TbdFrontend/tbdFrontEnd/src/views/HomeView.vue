<script setup>
import Paper from '@/components/Paper.vue'
fetch('https://api.ipify.org?format=json')
    .then(response => response.json())
    .then(data => {
        const ipAddress = data.ip;
        console.log('Tu dirección IP pública es:', ipAddress);
        fetch('http://ip-api.com/json/').then(response=> console.log(response))
    })
    .catch(error => {
        console.error('Error al obtener la dirección IP pública:', error);
    });
</script>

<template>
  <main>
    <Paper> </Paper>
  </main>
</template>
