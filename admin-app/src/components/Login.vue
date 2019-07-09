<template>
  <div id="app">
    <!-- login form -->
    <v-app id="login-card" v-show="isVisibleLogin">
      <v-content>
        <v-container fluid fill-height>
          <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
              <v-card class="elevation-12">

                <v-toolbar dark color="primary">
                  <v-toolbar-title>Doggis</v-toolbar-title>               
                </v-toolbar>

                <v-card-text>
                  <v-form ref="form" v-model="valid" lazy-validation>
                    <v-text-field prepend-icon="person" name="login" label="Usuário" type="text" v-model="userAuthentication.username" :rules="usernameRules" required></v-text-field>
                    <v-text-field prepend-icon="lock" name="password" label="Senha" id="password" type="password" v-model="userAuthentication.password" :rules="passwordRules" required></v-text-field>
                  </v-form>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="primary" @click="authenticate" :disabled="!valid">Entrar</v-btn>
                </v-card-actions>

              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
      </v-content>
    </v-app>

    <!-- progress loading -->
    <v-app id="login-loading" v-show="!isVisibleLogin">
      <v-content>
        <v-container fluid fill-height>
          <v-layout align-center justify-center>
            <v-progress-circular indeterminate color="primary" :size="100" :width="10"/>
          </v-layout>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>

import { mapState, mapActions } from 'vuex'

export default {
  data() {
    return {
      usernameRules: [
         v => !!v || 'Usuário é obrigatório',
         v => (v && v.length >= 3) || 'Usuário deve conter ao menos 3 caracteres'
      ],
      passwordRules: [
        v => !!v || 'Senha é obrigatória',
        v => (v && v.length >= 6) || 'Senha deve conter ao menos 6 caracteres'
      ],
      userAuthentication: {
        username: "",
        password: ""
      },
      isVisibleLogin: true,
      valid: true,
    }
  },
  computed: {
    ...mapState([
      'loggingIn',
      'loginError'
    ])
  },
  methods: {
    ...mapActions([
        'doLogin'
    ]),
    authenticate() {
      if (this.$refs.form.validate()) {
          this.doLogin(this.userAuthentication)
      } 
    }
  }
}
</script>

<style>
</style>