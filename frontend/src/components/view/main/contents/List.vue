<template>
    <div id="list">
        <div id="content">
            <div class="section_tit frst">
                <h3>{{typeName}}<span class="tit_dsc"> 총 {{approvalList.length}} 건</span>
                </h3>
                <table id="approval-list">
                    <thead>
                        <tr>
                            <th scope="col">문서번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">기안자</th>
                            <th scope="col">승인자</th>
                            <th scope="col">승인상태</th>
                            <th scope="col">기안일</th>
                            <th scope="col">완료일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(approval, idx) in approvalList" :key="idx">
                            <td>{{approval.approvalId}}</td>
                            <td><router-link :to="`view/${approval.approvalId}`">
                                {{approval.title}}</router-link></td>
                            <td>{{approval.requestMemberName}}</td>
                            <td>{{approval.approveMemberName}}</td>
                            <td>{{approval.requestApprovalType}}</td>
                            <td>{{approval.registerDate}}</td>
                            <td>{{approval.approveDate}}</td>
                        </tr>
                    </tbody>
            </table>
            </div>
        </div>
    </div>
</template>
			
			

<script>
import EventBus from '@/js/eventBus'
import axios from 'axios'

const REQUEST_APPROVAL_CODE = {
    REQUEST_WAIT: '요청중 문서',
    APPROVE_COMPLETE: '승인 완료',
    RETURN_COMPLETE: '반려 완료'
}

export default {
  name: 'List',
  created: function() {
      EventBus.$on('deliverSearchParam', (params, typeName) => {
          this.typeName = typeName;
          this.searchParam = params;
          this.getApprovalList();
      })
  },
    methods: {
        getApprovalList() {
            axios.get('/api/approval/getApprovalList', {params: this.searchParam})
                .then(res => {
                    if(res.data.code === 0) {
                        res.data.body.forEach(approval => {
                            approval.requestApprovalType = REQUEST_APPROVAL_CODE[approval.approvalStatusCode+'_'+approval.requestStatusCode];
                        })
                    this.approvalList = res.data.body;
                    }
                });
        }
    },
  data () {
    return {
        searchParam: {},
        approvalList: [],
        typeName: ''
    }
  }
}
</script>
