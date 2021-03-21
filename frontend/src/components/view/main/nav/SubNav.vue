<template>
    <div class="snb_nav">
        <div class="nav_lst frst">
            <h3 v-show="loginedMemberInfo.memberId != 1">
                <div class="big_menu"><router-link to="/register">기안 신청하기</router-link></div>
            </h3>
        </div>
        <div class="nav_lst">
            <h3> <a class="big_menu" @click="goRecentList()">{{APPROVAL_TYPE.RECENT}}</a></h3>
            <h3> <a class="big_menu" @click="setSearchParam('WAIT', null, loginedMemberInfo.memberId, loginedMemberInfo.memberId, 'WAIT')">{{APPROVAL_TYPE.WAIT}}</a></h3>
            <ul>
                <li><a class="small_menu" @click="setSearchParam('WAIT', null, null, loginedMemberInfo.memberId, 'WAIT_APPROVE')">{{APPROVAL_TYPE.WAIT_APPROVE}}</a></li>
                <li><a class="small_menu" @click="setSearchParam('WAIT', null, loginedMemberInfo.memberId, null, 'WAIT_REQUEST')">{{APPROVAL_TYPE.WAIT_REQUEST}}</a></li>
            </ul>
            <h3><a class="big_menu" @click="setSearchParam('COMPLETE', null, loginedMemberInfo.memberId, loginedMemberInfo.memberId, 'COMPLETE')">{{APPROVAL_TYPE.COMPLETE}}</a></h3>
            <ul>
                <li><a class="small_menu" @click="setSearchParam('COMPLETE', 'APPROVE', loginedMemberInfo.memberId, loginedMemberInfo.memberId, 'COMPLETE_APPROVE')">{{APPROVAL_TYPE.COMPLETE_APPROVE}}</a></li>
                <li><a class="small_menu" @click="setSearchParam('COMPLETE', 'RETURN', loginedMemberInfo.memberId, loginedMemberInfo.memberId, 'COMPLETE_RETURN')">{{APPROVAL_TYPE.COMPLETE_RETURN}}</a></li>
            </ul>
        </div>
    </div>
</template>



<script>
import EventBus from '@/js/eventBus'

export default {
    name: 'SubNav',
    methods: {
        goRecentList() {
            EventBus.$emit('showDefault', true);
        },
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
        }
    },
    data() {
        return {
            loginedMemberInfo: this.$store.state.loginMember.member,
            approvalList: [],
            APPROVAL_TYPE: {
                RECENT: '최근 결재 문서',
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