// router.js
import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/view/Main.vue'
import Login from '@/view/Login.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/main', component: Main },
    { path: '/login', component: Login }
]

const router = new VueRouter({
    routes
})

export default router
