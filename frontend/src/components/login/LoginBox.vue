<template>
    <div class="loginbox">
        <div>
            <div style="padding-top: 100px" 
                @click='login($attrs.account)'>
              <a style="cursor: pointer;">  <span style="font-size: 20px; font-wigth: bold;">
                    {{this.$attrs.account.name}}</span>
                <span style="font-size: 15px">    {{this.$attrs.account.gradeName}}</span></a></div>
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

<style>
.loginbox {
    border: 1px solid black;
    flex:1;
    width:25%;
    height: 300px;
    box-sizing: border-box;
}
</style>
