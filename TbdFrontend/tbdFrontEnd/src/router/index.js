import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import TableView from '../views/TableView.vue'
import TareaDetail from '@/views/TareaDetail.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/tarea/new',
      name: 'newTarea',
      component: () => import('../views/TareaCreate.vue')
    },
    {
      path: '/tarea/:id',
      name: 'detailTarea',
      component: TareaDetail
    },
    {
      path: '/table',
      name: 'table',
      component: TableView
    }
  ]
})

export default router
