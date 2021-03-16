<template>
    <div>
        contents 초기화면입니다.
        <Default></Default>
        <List></List>
    </div>
</template>

<script>

import Default from '@/components/view/main/contents/Default'
import List from '@/components/view/main/contents/List'

export default {
  name: 'Contents',
  components: {
      'Default': Default,
      'List': List
  },
  data() {
    return {
    }
  }
}
</script>

