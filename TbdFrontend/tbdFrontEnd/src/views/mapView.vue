<script setup>
import { ref, onMounted } from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import NavBar from '@/components/NavBar.vue';
import geoService from '@/services/geo.service.js'
import { useRoute } from 'vue-router';
import volMarker from '/src/assets/volMarker3.png';

const route = useRoute()
const id = ref(route.params.id)

const map = ref(null);

onMounted(() => {
  map.value = L.map('map').setView([-33.71664785998434, -70.9230223902269], 5);

  L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Tiles style by <a href="https://www.hotosm.org/" target="_blank">Humanitarian OpenStreetMap Team</a> hosted by <a href="https://openstreetmap.fr/" target="_blank">OpenStreetMap France</a>'
  }).addTo(map.value);
  let geoJsonData = {};

  geoService.getGeoJsonByEmergency(id.value).then((response) => {
    geoJsonData = response

    const geojsonLayer = L.geoJSON(geoJsonData, {
      onEachFeature: function (feature, layer) {
        // Personaliza el contenido del popup
        const popupContent = "<b>" + feature.properties.id + " </b> - " +
          feature.properties.nombre + "<br/>" + "<b> RUT: </b> " + feature.properties.rut
          + "<br/>" + "<b>Direccion: </b> " + feature.properties.direccion
          + "<br/>" + "<b> Telefono: </b> " + feature.properties.telefono
          + "<br/>" + "<b> Email: </b> " + feature.properties.email;

        // Agrega el popup al marcador o a la capa según el tipo de geometría
        if (feature.geometry.type === 'Point') {
          layer.bindPopup(popupContent);
        }
      }, pointToLayer: function (feature, latlng) {
        // Crea un marcador personalizado con un icono personalizado
        return L.marker(latlng, {
          icon: L.icon({
            iconUrl: volMarker,
            iconSize: [50, 50],  // Tamaño del icono
            iconAnchor: [25, 50], // Posición del ancla del icono
            popupAnchor:  [0, -50] // point from which the popup should open relative to the iconAnchor
          })
        });
      }
    }).addTo(map.value)
    // Ajusta la vista del mapa para que todas las características del GeoJSON estén visibles
    map.value.fitBounds(geojsonLayer.getBounds(), { padding: [0, 25] });
  })
})

</script>

<template>
  <NavBar></NavBar>
  <div class="main">
    <h4 style="margin-bottom: 20px;">Voluntarios en la emergencia</h4>
    <div id="map" ref="map" class="leaflet-map"></div>
  </div>
</template>

<style scoped>
.main {
  margin-top: 7vh;
  height: 85vh;
  width: 60vw;
  overflow: clip;
  padding: 10px;
  background-color: white;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
  border-radius: 25px;
  text-align: center;
  align-content: end;
}

.leaflet-map {
  height: 90%;
  border-radius: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
}
</style>