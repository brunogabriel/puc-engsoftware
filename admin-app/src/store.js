import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from './router'
import Constants from './constants'

Vue.use(Vuex)

axios.interceptors.request.use(function (config) {
  config.headers.Authorization = localStorage.getItem('accessToken')
  return config
})

export default new Vuex.Store({
    state: {
      accessToken: null,
      loggingIn: false,
      loginError: null
    },
    mutations: {
      loginStart: state => state.loggingIn = true,
      loginStop: (state, errorMessage) => {
        state.loggingIn = false
        state.loginError = errorMessage
      },
      updateAccessToken: (state, accessToken) => {
        state.accessToken = accessToken
      },
      logout: (state) => {
        state.accessToken = null
      }
    },
    actions: {
      doLogin({ commit }, loginData) {
        commit('loginStart')
        axios.post(Constants.API_AUTHENTICATION, {
          ...loginData
        })
        .then(response => {
          localStorage.setItem('accessToken', response.data.token)
          commit('loginStop', null)
          commit('updateAccessToken', response.data.token)
          router.push('/home')
        })
        .catch(error => {
          commit('loginStop', error.response.data.error)
          commit('updateAccessToken', null)
        })
      },
      fetchAccessToken({ commit }) {
        commit('updateAccessToken', localStorage.getItem('accessToken'))
      },
      logout({ commit }) {
        localStorage.removeItem('accessToken')
        commit('logout')
        router.push('/login')
      }
    }
  })
  