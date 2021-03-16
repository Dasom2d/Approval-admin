<template>
  <div >
      로그인이다 쫘식아
    <login-box></login-box>
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