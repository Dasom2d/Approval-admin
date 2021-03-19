import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import LoginPage from '@/components/login/Login'
import LoginSuccess from '@/components/login/LoginSuccess'
import MainPage from '@/components/view/main/Main'
import DetailPage from '@/components/view/detail/Detail'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      component: LoginPage
    },
    {
      path: '/loginSucess',
      component: LoginSuccess
    },
    {
      path: '/main',
      component: MainPage
    },
    {
      name: 'register',
      path: '/register',
      component: DetailPage,
      props: true
    },
    {
      name: 'view',
      path: '/view/:id',
      component: DetailPage
    }
  ]
})
