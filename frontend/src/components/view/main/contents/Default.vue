<template>
    <div id="default">
        <div id="content">
            <table id="recentApproval">
                <div id="main_content">
                    <div class="section_tit first main">
                        <h3>최근 결재문서</h3>
                    </div>
                    <div class="section_main">
                        <ul class="lst_type">
                            <li v-for="(approval, idx) in approvalList" :key="idx">
                                <em>{{approval.approvalType}}</em>
                                <p>
                                    <router-link :to="`view/${approval.approvalId}`">
                                        {{approval.title}}</router-link>
                                </p>
                                <span>
                                        <a>{{approval.requestMemberName}}</a>
                                        <span>{{approval.registerDate}}</span>
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
            </table>
        </div>
    </div>
</template>


<script>
import axios from 'axios'


export default {
    name: 'Default',
    mounted: function() {
        let params = {
            requestMemberId: this.loginedMemberInfo.memberId,
            approveMemberId: this.loginedMemberInfo.memberId
        }
        axios.get('/api/approval/getApprovalList', { params: params })
            .then(res => {
                if (res.data.code === 0) {
                    res.data.body.forEach(approval => {
                        approval.requestStatusCode === 'COMPLETE' ?
                            approval.approvalType = '완료문서' : approval.approvalType = '대기문서';
                    })
                    this.approvalList = res.data.body;
                }
            });
    },
    methods: {},
    data() {
        return {
            loginedMemberInfo: this.$store.state.loginMember.member,
            approvalList: []
        }
    }
}
</script>
