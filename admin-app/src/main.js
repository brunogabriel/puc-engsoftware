import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import pt from 'vuetify/es5/locale/pt'


Vue.use(Vuetify, {
  theme: {
    primary: '#6D65E6',
    secondary: '#6D65E6',
    accent: '#D81B60'
  },
  lang: {
    locales: { pt },
    current: 'pt'
  }
})

new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
