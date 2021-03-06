const BASE_API = "http://localhost:9090/api/"

const API_AUTHENTICATION = "http://localhost:9090/login"
const API_PRODUCT_TYPES = BASE_API + "productTypes/"
const API_MANUFACTURERS = BASE_API + "manufacturers/"
const API_PRODUCTS = BASE_API + "products/"
const API_REPORTS = BASE_API + "reports/"
const API_SERVICE_EMPLOYEES = API_REPORTS + "services/employees"
const API_SERVICES = API_REPORTS + "services/"


export default {
    API_AUTHENTICATION: API_AUTHENTICATION,
    API_PRODUCT_TYPES: API_PRODUCT_TYPES,
    API_MANUFACTURERS: API_MANUFACTURERS,
    API_PRODUCTS: API_PRODUCTS,
    API_SERVICES: API_SERVICES,
    API_SERVICE_EMPLOYEES: API_SERVICE_EMPLOYEES,
}