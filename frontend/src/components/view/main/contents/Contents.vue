<template>
    <div>
        <div class='loader' v-if='loading'>
            <img src="../../../../assets/loading.gif"></div>
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
        EventBus.$on('changeContentState', state => {
            this.isDefaultShow = state;
        });
        EventBus.$on('changeLoading', isLoading => {
            this.loading = isLoading;
        });
    },
    methods: {},
    data() {
        return {
            loading: false,
            state: false,
            isDefaultShow: true
        }
    }
}
</script>

<style>
.loader {
    position: fixed;
    z-index: 99;
    left: 180px;
    top: 430px;
}
</style>