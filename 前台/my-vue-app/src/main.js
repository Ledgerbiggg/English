import Vue from 'vue'
import App from './App.vue'
import store from '@/js/store.js'
Vue.config.productionTip = false
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
new Vue({
  store,
  render: h => h(App),
}).$mount('#app')
