<template>
  <div>
    <v-app id="list-grid" style="background-color:white;">
      <v-toolbar flat color="white">
        <v-toolbar-title>Fabricantes</v-toolbar-title>
        <v-divider class="mx-2" inset vertical></v-divider>
        <v-spacer></v-spacer>

        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on }">
            <v-btn round color="primary" dark class="mb-2" v-on="on">Novo fabricante</v-btn>
          </template>
          <v-card>

            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>
            
            <v-card-text>
              <v-container grid-list-md>
                <v-layout wrap>
                  <v-flex>
                    <v-form ref="form" v-model="valid" lazy-validation>
                      <v-text-field v-model="editedItem.name" label="Nome"
                        :rules="nameRules" required></v-text-field>
                    </v-form>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn round color="primary" flat @click="close">Cancelar</v-btn>
              <v-btn round color="primary" flat @click="save" :disabled="!valid">Salvar</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

      </v-toolbar>

      <v-alert 
        v-model="responseErrorEnabled" 
        dismissible
        type="error">{{ responseErrorMessage }} 
      </v-alert>

      <v-data-table
        :headers="headers"
        :items="manufacturers"
        :pagination.sync="pagination"
        hide-actions>
        <template v-slot:items="props">
          <td class="text-xs">{{ props.item.id }}</td>
          <td class="text-xs">{{ props.item.name }}</td>
          <td class="justify-center layout px-0">
            <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
            <v-icon small @click="deleteItem(props.item)">delete</v-icon>
          </td>
        </template>
      </v-data-table>
    </v-app>
  </div>
</template>

<script>
import axios from "axios"
import Constants from "../constants"

export default {
  data() {
    return {
      dialog: false,
      manufacturers: [],
      responseErrorMessage: "",
      responseErrorEnabled: false,
      headers: [
        { text: "Id", value: "id" },
        { text: "Nome", value: "name" },
        { text: "Ações", value: "actions", sortable: false }
      ],
      pagination: {
        rowsPerPage: -1
      },
      editedIndex: -1,
      editedItem: {
        id: 0,
        name: ""
      },
      defaultItem: {
        id: 0,
        name: ""
      },
      valid: true,
      nameRules: [
         v => !!v || 'Nome é obrigatório'
      ]
    }
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1
        ? "Novo fabricante"
        : "Editar fabricante"
    }
  },
  created() {
    this.loadManufacturers()
  },
  methods: {
    editItem(item) {
      this.editedIndex = this.manufacturers.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },
    
    deleteItem(item) {
      const index = this.manufacturers.indexOf(item)
      if (confirm("Você quer remover o fabricante?")) {
        axios.delete(Constants.API_MANUFACTURERS + item.id)
          .then(response => {
              console.log(response)
              this.manufacturers.splice(index, 1)
          })
          .catch(error => {
            this.responseErrorMessage = "Não foi possível remover o fabricante"
            this.responseErrorEnabled = true
          })
      }
    },

    close() {
      this.dialog = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
        this.$refs.form.reset()
        this.valid = true
      }, 300)
    },

    save() {
      if (this.$refs.form.validate()) {
        const element = this.editedItem
        if (this.editedIndex > -1) {
          axios.put(Constants.API_MANUFACTURERS + element.id, {
            ...element
          })
          .then(response => {
            this.loadManufacturers()
          }).catch(error => {
            this.responseErrorMessage = "Não foi possível atualizar os dados do fabricante"
            this.responseErrorEnabled = true
          })
        } else {
          axios.post(Constants.API_MANUFACTURERS, {
            ...element
          })
          .then(response => {
            this.loadManufacturers()
          })
          .catch(error => {
            this.responseErrorMessage = "Não foi possível salvar o fabricante"
            this.responseErrorEnabled = true
          })
        }
        this.close()
      }
    },

    loadManufacturers() {
      axios
        .get(Constants.API_MANUFACTURERS)
        .then(response => {
          this.manufacturers = response.data
        })
        .catch(error => {
          this.manufacturers = []
          this.responseErrorMessage = "Não foi possível carregar os fabricantes"
          this.responseErrorEnabled = true
        })
    }
  }
}
</script>

<style scoped>
  .v-alert {
    width: 100%;
  }
</style>