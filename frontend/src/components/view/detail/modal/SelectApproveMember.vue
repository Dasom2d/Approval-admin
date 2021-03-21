<template>
    <div class="content" style="width: 380px; position: relative; right: 28px; height:400px;">
        <div class='loader' v-if='loading'>
            <img src="../../../../assets/loading.gif"></div>
        <div class="section_title2" style="width: 438px">
            <h2 class="fl h_tx">승인자 선택</h2>
            <p class="fr p_tx"></p>
        </div>
        <div style="padding-top: 20px;">
            <table class="tb_lst" style="width: 438px">
                <thead>
                    <th>사번</th>
                    <th>이름</th>
                    <th>직급</th>
                </thead>
                <tbody>
                    <tr v-for="(member, idx) in memberList" :key="idx">
                        <td>{{member.memberId}}</td>
                        <td><a style="cursor: pointer;" @click="deliverMemberInfo(member)">{{member.name}}</a></td>
                        <td>{{member.gradeName}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
// import EventBus from '@/js/eventBus'

export default {
    name: "SelectApproveMember",
    props: ['modal'],
    watch: {
        modal: function() {
            this.modal ? this.getMemberList() : '';
        }
    },
    methods: {
        getMemberList() {
            this.loading = true;
            axios.get('/api/member/getMemberList?memberId=' + this.$store.state.loginMember.member.memberId)
                .then(res => {
                    if (res.status === 200) {
                        this.loading = false;
                        this.memberList = res.data;
                    }
                }).catch(err => {
                    alert('조회에 실패하였습니다.');
                    console.log(err);
                });
        },
        deliverMemberInfo(member) {
            this.approveMember = member;
            this.$emit('approveMember', member, false);

        }
    },
    data() {
        return {
            loading: false,
            approveMember: {},
            memberList: []
        }
    }
};
</script>
