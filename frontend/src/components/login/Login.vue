<template>
  <div >
      로그인이다 쫘식아
    <login-box :account="LION" @click="login('lion')"></login-box>
    <login-box :account="TUBE" @click="login('tube')"></login-box>
    <login-box :account="APEACH" @click="login('apeach')"></login-box>
    <login-box :account="MUJI" @click="login('con')"></login-box>
  </div>
</template>

<script>
import LoginBox from '@/components/login/LoginBox'



export default {
  name: 'App',
  props: 'account',
  components: {
    'LoginBox': LoginBox
  },
  methods: {},
  data() {
      return {
            LION: {
              memberId: '1',
              name: '라이언',
              gradeId: '1'
            },
            TUBE: {
              memberId: '2',
              name: '튜브',
              gradeId: '2'
            },
            APEACH: {
              memberId: '3',
              name: '어피치',
              gradeId: '3'
            },
            MUJI: {
              memberId: '4',
              name: '무지',
              gradeId: '4'
            }
      }
    }
}
</script>