<template>
    <div>
        <div class='loader' v-if='loading'>
            <img src="../../../../assets/loading.gif"></div>
        <div>
            <Default v-show="isDefaultShow"></Default>
            <List v-show="!isDefaultShow"></List>
        </div>
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
        EventBus.$on('showDefault', isDefaultShow => {
            this.isDefaultShow = isDefaultShow;
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
    left: 900px;
    top: 300px;
}
</style>