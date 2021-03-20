import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import LoginPage from '@/components/login/Login'
import MainPage from '@/components/view/main/Main'
import DetailPage from '@/components/view/detail/Detail'
import ErrorPage from '@/components/error/Error'

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
      name: 'register',
      path: '/register',
      component: DetailPage,
      props: true
    },
    {
      name: 'view',
      path: '/view/:id',
      component: DetailPage
    },
    {
      name: 'error',
      path: '/error',
      component: ErrorPage
    }
  ]
})
