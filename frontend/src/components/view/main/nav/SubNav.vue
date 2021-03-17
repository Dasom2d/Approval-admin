<template>
    <div id="sub_nav">
        <div class="nav_lst first" style="padding-top:14px;padding-bottom:14px;">
            <h3><router-link to="/register">기안 신청하기</router-link></h3>
        </div>
        <div class="nav_lst">
            <h3 @click="setSearchParam('WAIT')">진행중인 결재 문서 </h3>
            <ul>
                <li @click="setSearchParam('WAIT', null, $store.state.memberInfo.memberId)">(윗사람이)결재할 문서</li>
                <li @click="setSearchParam('WAIT', null, null, $store.state.memberInfo.memberId)">(내가)상신/결재한 문서</li>
            </ul>
            <h3 @click="setSearchParam('COMPLETE')">완료된 결재 문서</h3>
            <ul>
                <li @click="setSearchParam('COMPLETE', null, $store.state.memberInfo.memberId)">완료문서</li>
                <li @click="setSearchParam('COMPLETE', 'RETURN', $store.state.memberInfo.memberId)">반려문서</li>
            </ul>
        </div>
    </div>
</template>



<script>
import EventBus from '@/js/eventBus'

export default {
  name: 'SubNav',
  methods: {
      setSearchParam (requestStatusCode, approvalStatusCode, requestMemberId, approveMemberId) {
          let param = {
              requestStatusCode: requestStatusCode,
              approvalStatusCode: approvalStatusCode,
              requestMemberId: requestMemberId,
              approveMemberId: approveMemberId
          }
          EventBus.$emit('deliverSearchParam', param);

      }
  },
  data() {
    return {
        approvalList: []
      }
  }
}
</script>