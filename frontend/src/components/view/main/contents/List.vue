<template>
    <div id="list">
        <div id="content">
            <div class="section_tit frst">
                <h3 style="padding-bottom: 15px;">{{typeName}}<span class="tit_dsc"> 총 {{approvalList.length}} 건</span>
                </h3>
                <table class="tb_lst">
                    <colgroup>
                        <col style="width: 67px;">
                        <col>
                        <col style="width: 93px;">
                        <col style="width: 65px;">
                        <col style="width: 65px;">
                        <col style="width: 107px;">
                        <col style="width: 107px;">
                    </colgroup>
                    <thead>
                        <tr>
                            <th scope="col" class="bg_lft">문서번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">기안자</th>
                            <th scope="col">승인자</th>
                            <th scope="col">승인상태</th>
                            <th scope="col">기안일</th>
                            <th scope="col" class="bg_rgt">완료일</th>
                        </tr>
                    </thead>
                    <tbody v-if="approvalList.length > 0">
                        <tr v-for="(approval, idx) in approvalList" :key="idx" class="noline context" style="background-color: rgb(255, 255, 255);">
                            <td>{{approval.approvalId}}</td>
                            <td>
                                <a>
                                    <router-link :to="`view/${approval.approvalId}`">
                                        {{approval.title}}</router-link>
                                </a>
                            </td>
                            <td>{{approval.requestMemberName}}</td>
                            <td>{{approval.approveMemberName}}</td>
                            <td>{{approval.requestApprovalType}}</td>
                            <td>{{approval.registerDate}}</td>
                            <td>{{approval.approveDate}}</td>
                        </tr>
                    </tbody>
                    <tbody v-if="approvalList.length == 0">
                        <tr>
                            <td colspan="7">문서가 없습니다.</td>
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
    REQUEST_WAIT: '요청중',
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
            EventBus.$emit('changeLoading', true);
            axios.get('/api/approval/getApprovalList', { params: this.searchParam })
                .then(res => {
                    if (res.data.code === 0) {
                        EventBus.$emit('changeLoading', false);
                        res.data.body.forEach(approval => {
                            approval.requestApprovalType = REQUEST_APPROVAL_CODE[approval.approvalStatusCode + '_' + approval.requestStatusCode];
                        })
                        this.approvalList = res.data.body;
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
            searchParam: {},
            approvalList: [],
            typeName: ''
        }
    }
}
</script>
