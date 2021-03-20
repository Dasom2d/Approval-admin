<template>
  <div >
      로그인이다 쫘식아
     <login-box v-for="(member, idx) in memberList" :key="idx" :account="member"></login-box>
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
  mounted() {
  },
  data() {
      return {
            memberList: [
              {
                memberId: 1,
                name: '라이언',
                gradeId: 1,
                gradeName: '회장'
              },
              {
                memberId: 2,
                name: '튜브',
                gradeId: 2,
                gradeName: '과장'
              },
              {
                memberId: 3,
                name: '네오',
                gradeId: 3,
                gradeName: '대리'
              },
              {
                memberId: 4,
                name: '무지',
                gradeId: 4,
                gradeName: '사원'
              }
            ]
      }
    }
}
</script>