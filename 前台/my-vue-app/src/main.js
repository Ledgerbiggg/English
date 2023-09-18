import Vue from 'vue'
import App from './App.vue'
import store from '@/js/store.js'
import router from '@/js/router.js' // 引入路由配置

import * as echarts from 'echarts';
Vue.prototype.$echarts = echarts;
Vue.use(echarts)

Vue.config.productionTip = false
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

if (process.env.NODE_ENV === 'production') {
  if (window) {
    window.console.log = function () {};
  }
}

Vue.use(ElementUI);
new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
