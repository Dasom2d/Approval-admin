<template>
  <div id='container'>
    <div>{{name}} 님의 문서</div>
      <button @click="$router.push({path: '/main'})">메인으로</button>
      <!-- <ApprovalFormat></ApprovalFormat> -->
  </div>
</template>


<script>
// import ApprovalFormat from '@/components/view/detail/ApprovalFormat'

export default {
  name: 'ApprovalFormat',
  components: {
    // 'ApprovalFormat': ApprovalFormat
  },
  data() {
    return {
      name: '죠르디'
    }
  }
}
</script>
