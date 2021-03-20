<template>
    <div class="content">
        <h1>승인자 선택</h1>
        <div>
            <h2>승인자를 선택해주세요</h2>
            <table>
                <thead>
                    <th>사번</th>
                    <th>이름</th>
                    <th>직급</th>
                </thead>
                <tbody>
                    <tr v-for="(member, idx) in memberList" :key="idx">
                        <td>{{member.memberId}}</td>
                        <td><a @click="deliverMemberInfo(member)">{{member.name}}</a></td>
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
    props: {},
    mounted() {
        this.getMemberList();
    },
    methods: {
        getMemberList() {
            axios.get('/api/member/getMemberList?memberId=' + this.$store.state.loginMember.member.memberId)
                .then(res => {
                    if (res.statusText === 'OK') {
                        this.memberList = res.data;
                    }
                });
        },
        deliverMemberInfo(member) {
            this.approveMember = member;
            this.$emit('approveMember', member, false);

        }
    },
    data() {
        return {
            approveMember: {},
            memberList: []
        }
    }
};
</script>
