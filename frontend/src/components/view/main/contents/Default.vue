<template>
    <div id="default">
        <div class='loader' v-if='loading'>
            <img src="../../../../assets/loading.gif">
        </div>
        <table class="mainContentTable">
            <colgroup>
                <col width="*">
                <col width="248px">
                <col width="248px">
                <col width="248px">
            </colgroup>
            <tbody>
                <tr>
                    <td class="mainContentTableTd1">
                        <div id="main_content">
                            <div class="section_tit first main">
                                <h3>최근 결재문서</h3>
                            </div>
                        </div>
                        <div class="section_main v2">
                            <ul class="lst_type" v-if="approvalList.length > 0">
                                <li v-for="(approval, idx) in approvalList" :key="idx">
                                    <em class="situ">{{approval.approvalType}}</em>
                                    <p class="tit">
                                        <a>
                                            <router-link :to="`view/${approval.approvalId}`">
                                                {{approval.title}}</router-link>
                                        </a>
                                    </p>
                                    <span class="rgt" style="width:300px; margin-right:5px;">
                                                        <a>{{approval.requestMemberName}}</a>
                                                        <span class="date">{{approval.registerDate}}</span>
                                    </span>
                                </li>
                            </ul>
                            <ul v-if="approvalList.length == 0" class="lst_type">
                                <li> 문서가 없습니다.
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>


<script>
import axios from 'axios'
import EventBus from '@/js/eventBus'


export default {
    name: 'Default',
    mounted: function() {
        let params = {
            requestMemberId: this.loginedMemberInfo.memberId,
            approveMemberId: this.loginedMemberInfo.memberId
        }
        this.getRecentApprovalList(params);
    },
    methods: {
        getRecentApprovalList(params) {
            EventBus.$emit('changeLoading', true);
            axios.get('/api/approval/getApprovalList', { params: params })
                .then(res => {
                    this.loading = false;
                    if (res.data.code === 0) {
                        res.data.body.forEach(approval => {
                            approval.requestStatusCode === 'COMPLETE' ?
                                approval.approvalType = '완료문서' : approval.approvalType = '대기문서';
                        })
                        this.approvalList = res.data.body;
                        EventBus.$emit('changeLoading', false);
                    }
                }).catch(err => {
                    alert('조회에 실패하였습니다.');
                    console.log(err);
                    EventBus.$emit('changeLoading', false);
                });
        }
    },
    data() {
        return {
            loading: false,
            loginedMemberInfo: this.$store.state.loginMember.member,
            approvalList: []
        }
    }
}
</script>
