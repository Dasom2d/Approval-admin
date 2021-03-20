<template>
    <div id="sub_nav">
        <div class="nav_lst first" style="padding-top:14px;padding-bottom:14px;">
            <h3>
                <router-link to="/register">기안 신청하기</router-link>
            </h3>
        </div>
        <div class="nav_lst">
            <h3 @click="setSearchParam('WAIT', null, loginedMemberInfo.memberId, loginedMemberInfo.memberId, 'WAIT')">{{APPROVAL_TYPE.WAIT}} </h3>
            <ul>
                <li @click="setSearchParam('WAIT', null, null, loginedMemberInfo.memberId, 'WAIT_APPROVE')">{{APPROVAL_TYPE.WAIT_APPROVE}}</li>
                <li @click="setSearchParam('WAIT', null, loginedMemberInfo.memberId, null, 'WAIT_REQUEST')">{{APPROVAL_TYPE.WAIT_REQUEST}}</li>
            </ul>
            <h3 @click="setSearchParam('COMPLETE', null, loginedMemberInfo.memberId, loginedMemberInfo.memberId, 'COMPLETE')">{{APPROVAL_TYPE.COMPLETE}}</h3>
            <ul>
                <li @click="setSearchParam('COMPLETE', 'APPROVE', loginedMemberInfo.memberId, loginedMemberInfo.memberId, 'COMPLETE_APPROVE')">{{APPROVAL_TYPE.COMPLETE_APPROVE}}</li>
                <li @click="setSearchParam('COMPLETE', 'RETURN', loginedMemberInfo.memberId, loginedMemberInfo.memberId, 'COMPLETE_RETURN')">{{APPROVAL_TYPE.COMPLETE_RETURN}}</li>
            </ul>
        </div>
    </div>
</template>



<script>
import EventBus from '@/js/eventBus'

export default {
    name: 'SubNav',
    methods: {
        setSearchParam(requestStatusCode, approvalStatusCode, requestMemberId, approveMemberId, type) {
            let typeName = this.APPROVAL_TYPE[type];
            let param = {
                requestStatusCode: requestStatusCode,
                approvalStatusCode: approvalStatusCode,
                requestMemberId: requestMemberId,
                approveMemberId: approveMemberId
            }
            EventBus.$emit('deliverSearchParam', param, typeName);
            EventBus.$emit('changeContentState', false);
            EventBus.$emit('changeLoading', true);
        }
    },
    data() {
        return {
            loginedMemberInfo: this.$store.state.loginMember.member,
            approvalList: [],
            APPROVAL_TYPE: {
                WAIT: '진행중인 결재 문서',
                WAIT_APPROVE: '결재할 문서',
                WAIT_REQUEST: '상신/결재한 문서',
                COMPLETE: '완료 문서',
                COMPLETE_APPROVE: '승인 문서',
                COMPLETE_RETURN: '반려 문서'
            }
        }
    }
}
</script>