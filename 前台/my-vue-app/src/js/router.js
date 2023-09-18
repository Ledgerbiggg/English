// router.js
import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/view/Main.vue'
import Login from '@/view/Login.vue'
import {Message} from "element-ui";

Vue.use(VueRouter)

const routes = [
    {path: '/main', component: Main},
    {path: '/login', component: Login}
]

const router = new VueRouter({
    mode: 'history',
    routes
})

// 为路由对象，添加beforeEach导航守卫
router.beforeEach((to, from, next) => {
    //如果用户访问的登录页，直接放行
    if (to.path === '/login') return next()
    // 从sessionStorage中获取到保存的token值
    const tokenStr = window.localStorage.getItem('token')
    let unexpired = window.localStorage.getItem("unexpired");
    // 有token，而且未过期
    if(to.path!=='/main'){
        return next('/login')
    }
    if (tokenStr && unexpired) return next()
    if(tokenStr==null){
        Message.error('请先登录')
    }else {
        if (!unexpired){
            Message.error('身份认证过期')
        }
    }
    next('/login')
})

export default router
