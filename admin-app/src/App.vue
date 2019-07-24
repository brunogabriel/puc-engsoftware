<template>
<div id="app">
  <v-app v-if="accessToken && accessToken.length > 0">       
    <v-navigation-drawer :clipped="clipped" v-model="drawer" enable-resize-watcher app>
      <v-list dense class="pt-0">
        <v-list-tile
          v-for="item in items"
          :key="item.title"
          @click="doLogout(item.title)"
         :to="item.path == '#' ? '' : item.path" >
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
  
          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>      
    <v-toolbar fixed app :clipped-left="clipped">
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title>Admin Doggis</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>      
    <v-content>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-content>
  </v-app>
  <router-view v-else></router-view>
</div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  data () {
    return {
      drawer: true,
      clipped: true,
      items: [
        {
          icon: 'home',
          title: 'Página principal',
          path: '/home',
        },
        {
          icon: 'shopping_cart',
          title: 'Produtos',
          path: '/produtos',
        },
        {
          icon: 'subject',
          title: 'Fabricantes',
          path: '/fabricantes',
        },
        {
          icon: 'list',
          title: 'Tipos de produtos',
          path: '/tiposDeProduto',
        },
        {
          icon: 'print',
          title: 'Relatórios',
          path: '/relatorios',
        },
        {
          icon: 'exit_to_app',
          title: 'Logout',
          path: '/login',
        },       
      ]
    }
  },
  computed: {
    ...mapState([
      'accessToken'
    ])
  },
  methods: {
    ...mapActions([
        'logout'
    ]),
    doLogout(title) {
      if (title === 'Logout') {
        this.logout()
      }
    }
  }
}
</script>

<style scoped>
  .v-content {
    background-color:white;
  }
  .v-navigation-drawer {
    padding-top: 8px;
  }
</style>