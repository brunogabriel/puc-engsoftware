<template>
  <div>
    <v-app id="list-grid">
      <v-toolbar flat color="white">
        <v-toolbar-title>Produtos</v-toolbar-title>
        <v-divider class="mx-2" inset vertical></v-divider>
        <v-spacer></v-spacer>

        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on }">
            <v-btn color="primary" dark class="mb-2" v-on="on">Novo produto</v-btn>
          </template>
          <v-card>

            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>
            
            <v-card-text>
              <v-container grid-list-md>
                <v-layout wrap>
                  <v-flex>
                    <v-text-field v-model="editedItem.name" label="Nome"/>
                    <v-text-field v-model="editedItem.specifications" label="Especificações"/>
                    <v-text-field v-model="editedItem.flavor" label="Sabor"/>
                    <v-text-field v-model="editedItem.material" label="Material"/>
                    <v-text-field v-model="editedItem.indication" label="Indicação"/>
                    <v-select v-model="editedItem.productType" :items="productTypes" 
                        item-text="name" item-value="name" label="Tipo de produto" return-object/>
                    <v-select v-model="editedItem.manufacturer" :items="manufacturers" 
                        item-text="name" item-value="name" label="Fornecedor" return-object/>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" flat @click="close">Cancelar</v-btn>
              <v-btn color="blue darken-1" flat @click="save">Salvar</v-btn>
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
        :items="products"
        :pagination.sync="pagination"
        hide-actions>
        <template v-slot:items="props">
          <td class="text-xs">{{ props.item.id }}</td>
          <td class="text-xs">{{ props.item.name }}</td>
          <td class="text-xs">{{ props.item.productType.name }}</td>
          <td class="text-xs">{{ props.item.manufacturer.name }}</td>
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
      products: [],
      productTypes: [],
      manufacturers: [],
      responseErrorMessage: "",
      responseErrorEnabled: false,
      headers: [
        { text: "Id", value: "id" },
        { text: "Nome", value: "name" },
        { text: "Tipo", value: "productType.name" },
        { text: "Fornecedor", value: "manufacturer.name" },
        { text: "Ações", value: "actions", sortable: false }
      ],
      pagination: {
        rowsPerPage: -1
      },
      editedIndex: -1,
      editedItem: {
        id: 0,
        name: "",
        specifications: "",
        flavor: "",
        material: "",
        indication: "",
        manufacturer: null,
        productType: null
      },
      defaultItem: {
        id: 0,
        name: "",
        specifications: "",
        flavor: "",
        material: "",
        indication: "",
        manufacturer: null,
        productType: null
      }
    }
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1
        ? "Novo produto"
        : "Editar produto"
    }
  },
  created() {
    this.loadProducts()
    this.loadManufacturers()
    this.loadProductTypes()
  },
  methods: {
    editItem(item) {
      this.editedIndex = this.products.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      const index = this.products.indexOf(item)
      if (confirm("Você quer remover o produto?")) {
        axios.delete(Constants.API_PRODUCTS + item.id)
          .then(response => {
              console.log(response)
              this.products.splice(index, 1)
          })
          .catch(error => {
            this.responseErrorMessage = "Não foi possível remover o produto"
            this.responseErrorEnabled = true
          })
      }
    },

    close() {
      this.dialog = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },

    save() {
      const element = this.editedItem
      if (this.editedIndex > -1) {
        axios.put(Constants.API_PRODUCTS + element.id, {
          ...element
        })
        .then(response => {
          this.loadProducts()
        }).catch(error => {
          this.responseErrorMessage = "Não foi possível atualizar os dados do produto"
          this.responseErrorEnabled = true
        })
      } else {
        axios.post(Constants.API_PRODUCTS, {
          ...element
        })
        .then(response => {
          this.loadProducts()
        })
        .catch(error => {
          this.responseErrorMessage = "Não foi possível salvar o produto"
          this.responseErrorEnabled = true
        })
      }
      this.close()
    },

    loadProducts() {
      axios
        .get(Constants.API_PRODUCTS)
        .then(response => {
          this.products = response.data
        })
        .catch(error => {
          this.products = []
          this.responseErrorMessage = "Não foi possível carregar os produtos"
          this.responseErrorEnabled = true
        })
    },

    loadProductTypes() {
        axios
        .get(Constants.API_PRODUCT_TYPES)
        .then(response => {
          this.productTypes = response.data
        })
        .catch(error => {})
    },

    loadManufacturers() {
        axios
        .get(Constants.API_MANUFACTURERS)
        .then(response => {
          this.manufacturers = response.data
        })
        .catch(error => {})
    }
  }
}
</script>

<style scoped>
  .v-alert {
    width: 100%;
  }
</style>