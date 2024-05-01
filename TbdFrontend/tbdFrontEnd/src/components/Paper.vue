<script setup>
import router from '@/router'
import authService from '@/services/auth.service'
import voluntariosService from '@/services/voluntarios.service'
import Multiselect from '@vueform/multiselect'
import { onBeforeMount, onBeforeUnmount, ref } from 'vue'
onBeforeMount(() => {
  localStorage.removeItem('token')
  document.body.classList = 'login'
})

onBeforeUnmount(() => {
  document.body.classList = ''
})

const logging = ref(true)
const showPassword = ref(false)
const error = ref(false)

const login = ref({
  username: '',
  password: ''
})

const register = ref({
  username: '',
  password: '',
  email: '',
  rolId: 2
})
const newVoluntario = ref({
  nombre: '',
  edad: 0,
  direccion: '',
  genero: '',
  email: '',
  telefono: '',
  rut: '',
  userId: 0
})

const clearError = () => {
  error.value = false
}

const handleRut = (event) => {
  event.target.value = insertarPunto(event.target.value)
  if (logging.value) login.value.username = event.target.value
  else register.value.username = event.target.value
}

const handleLogin = () => {
  authService.login(login.value).then((response) => {
    localStorage.setItem('token', response.Authorization)
    router.push('/tarea/new')
  })
}

const handleRegister = () => {
  authService
    .register(register.value)
    .then((response) => {
      newVoluntario.value.userId = response.userId
      newVoluntario.value.rut = register.value.username
      newVoluntario.value.email = register.value.email
      console.log(newVoluntario)
      voluntariosService
        .postVoluntario(newVoluntario.value)
        .then((responseVol) => {
          localStorage.setItem('token', response.Authorization)
          router.push('/tarea/new').catch((error) => {
            console.log(error)
          })
        })
        .catch(error.value)
    })
    .catch(() => {
      error.value = true
    })
}

const togglePassword = () => {
  showPassword.value = !showPassword.value
}

function insertarPunto(numero) {
  let cleanNum = numero.replace(/[^\dk]/gi, '')
  if (cleanNum.length > 9) {
    cleanNum = cleanNum.substring(1)
  }
  if (cleanNum.length > 1) {
    let num = cleanNum.substring(0, cleanNum.length - 1)
    let digito = cleanNum.substring(cleanNum.length - 1)
    let numeroPun = num.replace(/\B(?=(\d{3})+(?!\d))/g, '.')
    numeroPun = numeroPun + '-' + digito
    return numeroPun
  }
  return cleanNum
}

const changeForm = (event) => {
  event.preventDefault()
  logging.value = !logging.value
}
</script>

<template>
  <transition name="bounce">
    <div
      v-if="error"
      class="alert alert-danger mx-auto alert-dismissible carousel-fade show fixed-top text-center py-2 mt-3"
      style="width: 30%; min-width: 400px; min-height: 40px"
      role="alert"
    >
      <strong class="col-10">Error!</strong> Rut o Correo registrados previamente!
      <button
        type="btn"
        class="btn btn-close col-1"
        style="padding-top: 2%"
        @click="clearError"
        data-dismiss="alert"
        aria-label="Close"
      ></button>
    </div>
  </transition>
  <link
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    rel="stylesheet"
  />
  <footer class="bg-dark text-light fixed-bottom" @click="changeForm" style="cursor: pointer">
    <div class="row">
      <div class="text-md-center">
        <p v-if="logging">Si aun no tiene cuenta y desea registrarse Clickee aqui</p>
        <p v-else>Si desea iniciar sesion, clickee aqui</p>
      </div>
    </div>
  </footer>
  <transition name="bounce" mode="out-in">
    <div v-if="logging" class="container d-flex justify-content-around" key="login">
      <div class="row" style="width: 90%">
        <div class="column pt-4 d-flex text-center justify-content-center align-items-center">
          <div class="backPaper h2 voluTitle">Voluntariados</div>
        </div>
        <div class="column align-items-center justify-content-center">
          <div class="form-group">
            <label class="h4 mx-1 nameInput" for="exampleFormControlInput1">RUT</label>
            <input
              type="username"
              class="form-control form-control-lg expand"
              id="exampleFormControlInput1"
              @input="handleRut"
            />
          </div>
        </div>
        <div class="column align-items-center justify-content-center">
          <div class="form-group">
            <label class="h4 mx-1 nameInput" for="exampleFormControlInput1">Contraseña</label>
            <div class="input-group">
              <input
                class="form-control rounded-end-0"
                :type="showPassword ? 'text' : 'password'"
                v-model="login.password"
                style="font-size: 20px"
              />
              <div class="input-group-append">
                <button
                  class="btn btn-primary rounded-start-0"
                  type="button"
                  @click="togglePassword"
                >
                  <i v-if="showPassword" class="fas fa-eye-slash"></i>
                  <i v-else class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="column pb-4 d-flex align-items-center justify-content-center">
          <button type="button" @click="handleLogin" class="btn btn-primary btn-lg btn-block">
            Ingresar
          </button>
        </div>
      </div>
    </div>
    <div class="background" v-else key="register">
      <div class="overlay"></div>
      <div class="divider"></div>
      <div class="titleReg h2 voluTitle">Registro para Voluntario</div>
      <div class="regForm container-fluid">
        <div
          id="reg"
          class="form-group text-white d-flex row align-content-around justify-content-around"
        >
          <div class="d-flex">
            <div class="col">
              <label class="h4 mx-2 white regInput" for="rut">RUT</label>
              <input
                type="text"
                style="width: 90%"
                class="form-control expand"
                id="rut"
                @input="handleRut"
              />
            </div>
            <div class="col">
              <label class="h4 mx-2 white regInput" for="rut">Nombre</label>
              <input
                type="text"
                v-model="newVoluntario.nombre"
                class="form-control expand"
                id="name"
              />
            </div>
          </div>
          <div>
            <label class="h4 mx-2 regInput" for="email">Email</label>
            <input type="email" v-model="register.email" class="form-control" id="email" />
          </div>
          <div>
            <label class="h4 mx-2" for="pass">Contraseña</label>
            <div class="input-group">
              <input
                class="form-control rounded-end-0"
                :type="showPassword ? 'text' : 'password'"
                v-model="register.password"
                placeholder="Ingresa tu contraseña"
              />
              <div class="input-group-append">
                <button
                  class="btn btn-secondary rounded-start-0"
                  type="button"
                  @click="togglePassword"
                >
                  <i v-if="showPassword" class="fas fa-eye-slash"></i>
                  <i v-else class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>
          <div class="d-flex">
            <div class="col">
              <label class="h4 mx-2" for="edad">Edad</label>
              <input
                id="edad"
                v-model="newVoluntario.edad"
                style="width: 90%"
                type="number"
                class="form-control expand"
              />
            </div>
            <div class="col">
              <label class="h4 mx-2" for="pass">Genero</label>
              <Multiselect
                class="text-black multiGenero"
                v-model="newVoluntario.genero"
                :can-clear="false"
                :options="[
                  { value: 'Masculino', label: 'Masculino' },
                  { value: 'Femenino', label: 'Femenino' },
                  { value: 'Otro', label: 'Otro' }
                ]"
              />
            </div>
          </div>
          <div class="d-flex">
            <div class="col">
              <label class="h4 mx-2" for="pass">Celular</label>
              <div class="input-group" style="height: fit-content; width: 90%">
                <div class="input-group-prepend" style="width: 50px">
                  <span class="input-group-text" style="border-radius: 6px 0px 0px 6px">+56</span>
                </div>
                <input
                  type="text"
                  v-model="newVoluntario.telefono"
                  class="form-control"
                  placeholder="912345678"
                />
              </div>
            </div>
            <div class="col">
              <label class="h4 mx-2" for="edad">Dirección</label>
              <input id="edad" v-model="newVoluntario.direccion" class="form-control" />
            </div>
          </div>
          <div class="row">
            <button type="submit" @click="handleRegister" class="btn btn-primary btn-block my-3">
              Registrarse
            </button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<style scoped>
.bounce-enter-active {
  animation: bounce-in 0.3s;
}
.bounce-leave-active {
  animation: bounce-in 0.3s reverse;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
}

#reg {
  height: 100%;
}
.form-control.expand {
  letter-spacing: 2px;
}

.form-control:focus {
  outline: 0;
  border-color: lightgray;
  box-shadow: 0 0 0 0.25rem rgba(255, 0, 204, 0.25); /* Ancho del anillo y opacidad */
}
.multiGenero {
  --ms-py: 0.37rem;
  --ms-radius: 6px;
  --ms-option-bg-selected: rgba(199, 50, 60, 1);
  --ms-option-bg-selected-pointed: rgb(180, 40, 48);
  --ms-tag-bg: rgba(199, 50, 60, 1);
  --ms-ring-color: rgba(255, 0, 204, 0.25);
}

.alert {
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.2);
}

.container {
  background-color: rgba(33, 33, 33, 0.2);
  width: 35vw;
  height: 70vh;
  border-radius: 25px;
}
.background {
  position: relative;
  width: 60vw;
  height: 80vh;
  background: linear-gradient(-45deg, rgba(0, 0, 0, 0.2), rgba(255, 255, 255, 0.2));
  border-radius: 25px;
  background-blend-mode: normal;
  mix-blend-mode: normal;
}

.loginreq {
  position: absolute;
  top: 5%;
  left: 50%;
  transform: translateX(-110%);
}
.overlay {
  position: absolute;
  top: 20%;
  left: 0%;
  width: 80%;
  height: 80%;
  background-image: url('/src/assets/backRegImg.png');
  background-position: bottom left;
  background-repeat: no-repeat;
  background-size: cover;
  background-position-x: 50%;
  background-blend-mode: luminosity;

  mix-blend-mode: luminosity;
}

.backPaper {
  padding: 2% 3%;
  background-color: white;
  border-radius: 8px;
}

.titleReg {
  position: absolute;
  top: 4%;
  left: 50%;
  padding: 6px 3%;
  transform: translateX(-50%);
  background-color: white;
  font-size: clamp(20px, 2vw, 3vw);
  border-radius: 8px;
}

.divider {
  position: absolute;
  top: 20%;
  width: 50%;
  height: 78%;
  border-right: 1px solid rgba(255, 255, 255, 1);
}

.regForm {
  position: absolute;
  top: 15%;
  left: 52%;
  width: 46%;
  height: 85%;
}

@media screen and (max-width: 620px) {
  .background {
    width: 90vw;
  }
}

@media screen and (max-width: 1200px) {
  .regForm {
    position: absolute;
    top: 15%;
    left: 5%;
    width: 90%;
    height: 85%;
  }
  .divider {
    display: none;
  }
}

.nameInput {
  color: white;
  font-weight: 200;
}

.btn-primary {
  color: white;
  background-color: #243346;
  border: 0;
  font-size: x-large;
  &:hover {
    filter: brightness(120%);
  }
}
.voluTitle {
  font-weight: 500;
  letter-spacing: 2px;
}
</style>
