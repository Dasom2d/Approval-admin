<template>
    <div id="sub_nav">
        <div class="nav_lst first" style="padding-top:14px;padding-bottom:14px;">
            <h3 @click="goRegister">기안 신청하기</h3>
        </div>
        <div class="nav_lst">
            <h3 @click="setSearchParam('WAIT')">진행중인 결재 문서</h3>
            <ul>
                <li @click="setSearchParam('WAIT', 'requestId', null)">결재할 문서</li>
                <li @click="setSearchParam('WAIT', null, 'approveId')">상신/결재한 문서</li>
            </ul>
            <h3 @click="setSearchParam('COMPLETE')">완료된 결재 문서</h3>
            <ul>
                <li @click="setSearchParam('COMPLETE', null, null, 'APPROVE')">완료문서</li>
                <li @click="setSearchParam('COMPLETE', null, null, 'RETURN')">반려문서</li>
            </ul>
        </div>
    </div>
</template>



<script>
import EventBus from '@/js/eventBus'

export default {
  name: 'SubNav',
  methods: {
      goRegister () {
          this.$router.push({name: 'register', params: {type: 'register'}});
      },
      setSearchParam (requestStatusCode, requestMemberId, approveMemberId, approveStatusCode) {
          let param = {
              requestStatusCode: requestStatusCode,
              approveStatusCode: approveStatusCode
          }
        requestMemberId != null ? 
              param.requestMemberId = this.$store.state.memberInfo.memberId : '';
        approveMemberId != null ? 
              param.approveMemberId = this.$store.state.memberInfo.memberId : '';

        
        EventBus.$emit('deliverSearchParam', param);

      }
  }
}
</script>