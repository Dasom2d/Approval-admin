<template>
  <div id="loginBox">
    <div>
      ㅎㅏ이 
      {{this.$store.state.memberInfo.name}}
      <router-link to='/main'>메인 이동</router-link> 
    </div>
  </div>
</template>


<script>

export default {
  name: 'LoginBox',
  components: {
  },
}
</script>
