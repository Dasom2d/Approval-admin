<template>
    <div>
        contents 초기화면입니다.
        <Default v-if="isDefaultShow"></Default>
        <List v-if="!isDefaultShow"></List>
    </div>
</template>

<script>
import EventBus from '@/js/eventBus'

import Default from '@/components/view/main/contents/Default'
import List from '@/components/view/main/contents/List'

export default {
  name: 'Contents',
  components: {
      'Default': Default,
      'List': List
  },
  created: function() {
      // let that = this;
      EventBus.$on('changeContentState', state => {
          this.isDefaultShow = state;
      });
  },
  methods: {
    changeContentState() {
    }
  },
  data() {
    return {
      state: false,
      isDefaultShow: true
    }
  }
}
</script>

