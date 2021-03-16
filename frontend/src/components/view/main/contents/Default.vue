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
                                <em>완료문서</em>
                                <p>{{approval.title}}</p>
                                <span>
                                    <a>{{approval.requestMemberName}}</a>
                                    <span>2021.03.16</span>
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
    mounted() {
        let params = {
            registerMemberId: this.$store.state.memberInfo.memberId
        }
        axios.get('/api/approval/getApprovalList', {params: params})
            .then(res => {
                if(res.data.code === 0) {
                    res.data.body.forEach(approval => {
                        approval.isCompleted = '완료문서';
                    })
                this.approvalList = res.data.body;
                }
            })
    },
    method: {
    },
    data() {
      return {
        approvalList: []
      }
    }
}
</script>
