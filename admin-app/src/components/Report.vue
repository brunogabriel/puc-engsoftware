<template>
  <div >
    <v-app id="list-grid" style="background-color:white;">

      <v-toolbar flat color="white">
        <v-toolbar-title>Relatórios</v-toolbar-title>
        <v-divider class="mx-2" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <template>
            <v-btn round color="primary" class="mb-2" @click="loadReports" :disabled="!valid">Filtrar</v-btn>
          </template>
      </v-toolbar>
    
      <v-alert 
        v-model="responseErrorEnabled" 
        dismissible
        type="error">{{ responseErrorMessage }} 
      </v-alert>
      <v-container grid-list-md>
        <v-layout row wrap>
          <v-flex xs12>
             <v-form ref="form" v-model="valid" lazy-validation>
               <v-layout row wrap>
                 <v-select class="mx-2" v-model="selectedEmployee" :items="employees" 
                 item-text="name" item-value="name" label="Funcionário" 
                 :rules="employeesRules" required return-object/>
                 <v-select class="mx-2" v-model="selectedMonth" :items="months" 
                 item-text="name" item-value="name" label="Mês" 
                 :rules="monthRules" required return-object/>
               </v-layout>
             </v-form>
          </v-flex>
          <v-flex xs12><br></v-flex>
          <v-flex xs12><br></v-flex>
          <v-flex xs12>
            <v-data-table :headers="headers"
            :items="reports"
            :pagination.sync="pagination" hide-actions>
              <template v-slot:items="props">
                <td class="text-xs">{{ props.item.employee }}</td>
                <td class="text-xs">{{ props.item.clientName }}</td>
                <td class="text-xs">{{ props.item.petName }}</td>
                <td class="text-xs">{{ props.item.type }}</td>
                <td class="text-xs">{{ props.item.duration }}</td>
                <td class="text-xs">{{ props.item.price }}</td>
                <td class="text-xs">{{ props.item.date }}</td>
              </template>
            </v-data-table>
          </v-flex>
        </v-layout>
      </v-container>
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
      selectedMonth: null,
      selectedEmployee: null,
      reports: [],
      employees: [],
      months: [
        {
          "name": "Janeiro",
          "value": 1
        },
        {
          "name": "Fevereiro",
          "value": 2
        },
        {
          "name": "Março",
          "value": 3
        },
        {
          "name": "Abril",
          "value": 4
        },
        {
          "name": "Maio",
          "value": 5
        },
        {
          "name": "Junho",
          "value": 6
        },
        {
          "name": "Julho",
          "value": 7
        },
        {
          "name": "Agosto",
          "value": 8
        },{
          "name": "Setembro",
          "value": 9
        },
        {
          "name": "Outubro",
          "value": 10
        },
        {
          "name": "Novembro",
          "value": 11
        },
        {
          "name": "Dezembro",
          "value": 12
        },
      ],
      responseErrorMessage: "",
      responseErrorEnabled: false,
      headers: [
        { text: "Funcionário", value: "employee" },
        { text: "Cliente", value: "clientName" },
        { text: "Pet", value: "petName" },
        { text: "Tipo de Serviço", value: "type" },
        { text: "Duração (min)", value: "duration" },
        { text: "Preço (R$)", value: "price" },
        { text: "Realização", value: "date" },
      ],
      pagination: {
        rowsPerPage: -1
      },
      valid: true,
      employeesRules: [
         v => !!v || 'Funcionário é obrigatório'
      ],
      monthRules: [
        v => !!v || 'Mês é obrigatório'
      ],
    }
  },
  created() {
    this.loadEmployees()
  },
  methods: {
    loadReports() {
      if (this.$refs.form.validate()) {
        axios
        .get(Constants.API_SERVICES + this.selectedEmployee + "/" + this.selectedMonth.value)
        .then(response => {
          this.reports = response.data
        }).catch(error => {
          this.reports = []
           this.responseErrorMessage = "Não foi possível carregar os produtos"
           this.responseErrorEnabled = true
        })
      } 
    },
    loadEmployees() {
      axios
        .get(Constants.API_SERVICE_EMPLOYEES)
        .then(response => {
          this.employees = response.data
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