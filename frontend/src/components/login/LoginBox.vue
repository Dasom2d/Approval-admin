<template>
    <div id="loginBox">
        <div style="border: 1px solid black;">
            ㅎㅏ이
            <div @click='login($attrs.account)'>{{this.$attrs.account.name}} {{this.$attrs.account.gradeName}} 로그인</div>
        </div>
    </div>
</template>


<script>
export default {
    name: 'LoginBox',
    components: {},
    methods: {
        login(account) {
            this.$store.commit('loginMember/LOGIN', account);
            this.$router.push({ path: '/main' })
        }
    }
}
</script>
