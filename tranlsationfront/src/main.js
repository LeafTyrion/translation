import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// import axios from 'axios'
// import element from 'element-ui'






createApp(App).use(store).use(router).mount('#app')

