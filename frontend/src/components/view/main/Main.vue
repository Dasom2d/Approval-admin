<template>
  <div id='container'>
    <div>{{this.$store.state.memberInfo.name}} 님의 문서</div>
      <button @click="$router.push({path: '/'})">로그아웃</button>
      <SuvNav></SuvNav>
      <Contents></Contents>
  </div>
</template>


<script>
import SuvNav from '@/components/view/main/nav/SuvNav'
import Contents from '@/components/view/main/contents/Contents'


export default {
  name: 'Main',
  components: {
    'SuvNav': SuvNav,
    'Contents': Contents
  },
  data() {
    return {
    }
  }
}
</script>
