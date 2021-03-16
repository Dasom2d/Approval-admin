<template>
  <div >
      로그인이다 쫘식아
    <login-box></login-box>
    <router-link to='/main'>메인 이동</router-link>
  </div>
</template>

<script>
import LoginBox from '@/components/login/LoginBox'

export default {
  name: 'App',
  components: {
    'LoginBox': LoginBox
  }
}
</script>