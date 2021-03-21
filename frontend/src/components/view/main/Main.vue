<template>
    <div id="container">
        <div style="padding-top: 20px">
            <span class="name">{{this.$store.state.loginMember.member.name}}</span>
            <span class="nim">님의 문서</span>
    
            <span style="padding-left: 10px;"><button @click="logout">로그아웃</button></span>
        </div>
        <div class="contents_btm" style="padding-top: 50px;">
            <div class="snb">
                <SubNav></SubNav>
            </div>
            <div id="content">
                <Contents></Contents>
            </div>
        </div>
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

<style>

#container {
    position: relative;
    width: 980px;
    margin: 0 auto;
    zoom: 1;
}

#container .name {
    font-size: 20px;
    font-weight: bold;
}

#container .nim {
    font-size: 15px;
}

.snb {
    float: left;
    width: 180px;
    margin: 18px 30px 0 0;
    text-align: left;
}

.snb .snb_nav .nav_lst.frst {
    padding-top: 14px;
    padding-bottom: 14px;
    margin: 0;
    border: 0;
}

div .big_menu {
    cursor: pointer;
    font-size: 16px;
    color: #222;
    text-decoration: none;
}

.small_menu {
    line-height: 18px;
    cursor: pointer;
    display: inline-block;
}


</style>