import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import LoginPage from '@/components/login/Login'
import MainPage from '@/components/view/main/Main'
import DetailPage from '@/components/view/Detail'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: LoginPage
    },
    {
      path: '/main',
      component: MainPage
    },
    {
      path: '/detail',
      alias: ['/register', '/modify', '/view'],
      component: DetailPage
    }
  ]
})
