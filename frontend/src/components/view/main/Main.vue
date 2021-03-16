<template>
  <div id='container'>
    <div>{{name}} 님의 문서</div>
      <button @click="$router.push({path: '/'})">로그아웃</button>
      <SuvNav></SuvNav>
      <!-- <Contents></Contents> -->
      <List></List>
  </div>
</template>


<script>
import SuvNav from '@/components/view/main/nav/SuvNav'
// import Contents from '@/components/view/main/contents/Contents'
import List from '@/components/view/main/contents/List'


export default {
  name: 'Main',
  components: {
    'SuvNav': SuvNav,
    // 'Contents': Contents,
    'List': List
  },
  data() {
    return {
      name: '죠르디'
    }
  }
}
</script>
