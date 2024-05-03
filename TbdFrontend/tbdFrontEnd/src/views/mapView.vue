<script setup>
import { ref, onMounted } from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import NavBar from '@/components/NavBar.vue';

const map = ref(null);

onMounted(() => {
  map.value = L.map('map').setView([-33.71664785998434, -70.9230223902269], 10);

  L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Tiles style by <a href="https://www.hotosm.org/" target="_blank">Humanitarian OpenStreetMap Team</a> hosted by <a href="https://openstreetmap.fr/" target="_blank">OpenStreetMap France</a>'
  }).addTo(map.value);

  const geojsonLayer = L.geoJSON({
    "type": "FeatureCollection",
    "features": [
      {
        "type": "Feature",
        "properties": {
          "name": "Coors Field",
          "show_on_map": true
        },
        "geometry": {
          "type": "Point",
          "coordinates": [
            -70.64827,
            -33.45694
          ]
        }
      },
      {
        "type": "Feature",
        "properties": {
          "name": "Coors Field",
        },
        "geometry": {
          "coordinates": [
            -70.17436868454367,
            -33.70850163044105
          ],
          "type": "Point"
        }
      },
      {
        "type": "Feature",
        "properties": {
          "name": "Coors Field",
        },
        "geometry": {
          "coordinates": [
            -71.07199156254852,
            -32.14443878910208
          ],
          "type": "Point"
        }
      },
      {
        "type": "Feature",
        "properties": {
          "name": "Coors Field",
        },
        "geometry": {
          "coordinates": [
            -71.28639322014887,
            -35.098131129474474
          ],
          "type": "Point"
        }
      },
      {
        "type": "Feature",
        "properties": {
          "name": "Coors Field",
        },
        "geometry": {
          "coordinates": [
            -70.24553795675347,
            -34.30661317232609
          ],
          "type": "Point"
        }
      },
      {
        "type": "Feature",
        "properties": {
          "name": "Coors Field",
        },
        "geometry": {
          "coordinates": [
            -71.55528082985957,
            -34.18472256767414
          ],
          "type": "Point"
        }
      },
      {
        "type": "Feature",
        "properties": {
          "name": "Coors Field",
        },
        "geometry": {
          "coordinates": [
            -70.30625451378509,
            -32.69376796205147
          ],
          "type": "Point"
        }
      },
      {
        "type": "Feature",
        "properties": {
          "name": "Coors Field",
        },
        "geometry": {
          "coordinates": [
            -71.30374080787232,
            -33.04345989652627
          ],
          "type": "Point"
        }
      }
    ]
  }, {
    onEachFeature: function (feature, layer) {
      // Personaliza el contenido del popup
      const popupContent = "<b>" + feature.properties.id + " </b> - " +
      feature.properties.name + "<br/>" + "<b> RUT: </b> " +feature.properties.rut 
      + "<br/>" + "<b>Direccion: </b> " + feature.properties.direccion
      + "<br/>" + "<b> Telefono: </b> " + feature.properties.telefono
      + "<br/>" + "<b> Email: </b> " + feature.properties.telefono
      ;

      // Agrega el popup al marcador o a la capa según el tipo de geometría
      if (feature.geometry.type === 'Point') {
        layer.bindPopup(popupContent);
      }
    }
  }).addTo(map.value)
  // Ajusta la vista del mapa para que todas las características del GeoJSON estén visibles
  map.value.fitBounds(geojsonLayer.getBounds());
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