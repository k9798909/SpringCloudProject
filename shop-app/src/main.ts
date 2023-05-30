import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'

//bootstrap
import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

//main.css
import './assets/main.css'

//fortawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {
  faArrowLeft,
  faArrowRight,
  faTrash,
  faShoppingCart,
  faClipboardList,
  faUser,
  faFileExcel
} from '@fortawesome/free-solid-svg-icons'
library.add({
  faShoppingCart,
  faArrowLeft,
  faTrash,
  faArrowRight,
  faClipboardList,
  faUser,
  faFileExcel
})

const app = createApp(App)
app.use(router)
app.use(BootstrapVue3)
app.use(createPinia())
app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app')
