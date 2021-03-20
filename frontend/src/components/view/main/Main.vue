<template>
    <div id='container'>
        <div>{{this.$store.state.loginMember.member.name}} 님의 문서</div>
        <button @click="logout">로그아웃</button>
        <SubNav></SubNav>
        <Contents></Contents>
    </div>
</template>


<script>
import SubNav from '@/components/view/main/nav/SubNav'
import Contents from '@/components/view/main/contents/Contents'

export default {
    name: 'Main',
    components: {
        'SubNav': SubNav,
        'Contents': Contents
    },
    mounted() {},
    methods: {
        logout() {
            this.$store.commit('loginMember/LOGOUT');
            this.$router.push({ path: '/' });
        }
    },
    data() {
        return {}
    }
}
</script>
