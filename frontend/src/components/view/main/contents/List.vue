<template>
    <div id="list">
        <div id="content">
            <div class="section_tit frst">
                <h3>완료문서<span class="tit_dsc"> 총 {{approvalList.length}} 건</span>
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
                            <td @click="goView(approval.approvalId)">{{approval.title}}</td>
                            <td>{{approval.requestMemberName}}</td>
                            <td>{{approval.approveMemberName}}</td>
                            <td>{{approval.approvalType}}</td>
                            <td>2021.03.12</td>
                            <td>2021.03.15</td>
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

export default {
  name: 'List',
  created: function() {
      let that = this;
      EventBus.$on('deliverSearchParam', function(params){
          that.searchParam = params;
          that.getApprovalList();
      })
  },
    methods: {
        goView(id) {
          this.$router.push({name: 'view', params: {type: 'view', approvalId: id}})
        },
        getApprovalList() {
            axios.get('/api/approval/getApprovalList', {params: this.searchParam})
                .then(res => {
                    if(res.data.code === 0) {
                        res.data.body.forEach(approval => {
                            approval.requestStatusCode === 'COMPLETE' ? 
                            approval.approvalType = '완료문서' : approval.approvalType  = '대기문서';
                        })
                    this.approvalList = res.data.body;
                    }
                });
        }
    },
  data () {
    return {
        searchParam: {},
        approvalList: []
    }
  }
}
</script>
