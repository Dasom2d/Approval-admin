<template>
    <div id="header">
        <div class="section_header" id="section_header">
            <div class="left">Lion Company</div>
            <div class="right">{{this.$store.state.loginMember.member.name}}<span class="nim"> 님</span>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Header',
    mounted() {},
    data() {
        return {}
    }
}
</script>

<style>
#header {
    width: 980px;
    margin: 10px auto;
    display: flex;
}

#header .section_header {
    width: 100%;
    height: 31px;
    margin: 0 auto;
    padding-top: 10px;
    background-color: antiquewhite
}

#header .left {
    float: left;
    font-weight: bold;
    font-size: 20px;
    padding-left: 30px;
}

#header .right {
    padding-right: 20px;
    font-size: 15px;
    float: right;
}

#header .nim {
    font-size: 11px;
}
</style>