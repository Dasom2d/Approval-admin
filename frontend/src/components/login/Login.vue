<template>
  <div >
      로그인이다 쫘식아
     <login-box v-for="(member, idx) in memberList" :key="idx" :account="member"></login-box>
  </div>
</template>

<script>
import LoginBox from '@/components/login/LoginBox'
import axios from 'axios'

export default {
  name: 'App',
  props: 'account',
  components: {
    'LoginBox': LoginBox
  },
  mounted() {
    this.getMemberList();
  },
  methods: {
    getMemberList() {
      axios.get('/api/member/getMemberList?memberId=5')
              .then(res => {
                  if(res.statusText === 'OK'){
                    this.memberList = res.data;
                  }
              });
    },
  },
  data() {
      return {
            memberList: []
      }
    }
}
</script>