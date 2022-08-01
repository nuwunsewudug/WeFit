import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import dotenv from 'dotenv'
import gAuthPlugin from 'vue3-google-oauth2'

const app = createApp(App)

dotenv.config()

let gauthClientId = process.env.VUE_APP_API_URL
app.use(gAuthPlugin, {
  clientId: gauthClientId,
  scope: 'email',
  prompt: 'consent',
})
  
app.use(store).use(router).mount('#app')
