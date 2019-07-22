import Vue from 'vue'
import Router from 'vue-router'
import store from './store'
import Login from './components/Login.vue'
import Home from './components/Home.vue'
import ProductType from './components/ProductType.vue'
import Manufacturer from './components/Manufacturer.vue'
import Product from './components/Product.vue'
import Report from './components/Report.vue'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/tiposDeProduto',
      name: 'Tipos de produto',
      component: ProductType
    },
    {
      path: '/fabricantes',
      name: 'Fabricantes',
      component: Manufacturer
    },
    {
      path: '/produtos',
      name: 'Produtos',
      component: Product
    },
    {
      path: '/relatorios',
      name: 'Relatórios',
      component: Report
    },
    {
      path: '*',
      redirect: '/login'
    }
  ]
})

const internalPaths = [
  "/home", "/tiposDeProduto", "/fabricantes", "/produtos", "/relatorios"
]
router.beforeEach((to, from, next) => {
  store.dispatch('fetchAccessToken')
  if (to.fullPath === '/login') {
    if (store.state.accessToken) {
      next('/home')
    }
  }
  if (to.fullPath in internalPaths) {
    if (!store.state.accessToken) {
      next('/login')
    }
  }
  next()
})

export default router;