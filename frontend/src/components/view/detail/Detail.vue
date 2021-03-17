<template>
  <div >
      기안신청서
    <div>
      <ul>
        <li>{{approvalState}}</li>
      </ul>
      <ul>
        <li>
          <div class='type'> 
            <span>요청자</span>
          </div>
          <div class='name'> 
            <span title='요청자'> {{requestMemberName}}</span>
          </div>
          <div class='date'> 
            <span title='요청일자'> {{registerDate}}</span>
          </div>
        </li>
        <li>
          <div class='type'> 
            <span>승인자</span>
          </div>
          <div class='name'> 
            <span title='요청자'> {{approveMemberName}}</span>
          </div>
          <div class='date'> 
            <span title='요청일자'> {{approveDate}}</span>
          </div>
        </li>
      </ul>
    </div>
    <div></div>
    <div></div>
    
    <div class="tbl_type2">
            <table>
                <colgroup><col style="width:130px"><col></colgroup>
                <tbody>
                <tr>
                    <th scope="col"><div class="th">제목</div></th>
                    <td><div class="td">
                        <input type="text" v-model="title" :disabled="type==='view'" title="제목 입력란" class="ipt_txt" placeholder="" style="width:200px">
                    </div></td>
                </tr>
                <tr>
                    <th scope="col"><div class="th">내용</div></th>
                    <td><div class="td">
                        <textarea v-model="content" :disabled="type==='view'" name="내용 입력란" cols="20" rows="3" class="ipt_txt" style="width:100%;height:56px"></textarea>
                    </div></td>
                </tr>
                </tbody>
            </table>
        </div>
    <div>
      <button @click="$router.push({path: 'main'})">목록으로</button>
      <button v-if="type==='register'" @click="registApproval()">등록하기</button>
      <button v-if="type==='view' && requestStatusCode === 'WAIT'" @click="goEdit()">편집</button>
      <button v-if="type==='view' && requestStatusCode === 'WAIT'" @click="deleteApproval()">삭제</button>
      <button v-if="type==='edit'" @click="updateApproval()">저장</button>
    </div>
  </div>

</template>


<script>

import axios from 'axios'

const REQUEST_STATUS_CODE = {
  REQUEST: '요청중',
  APPROVE: '승인',
  RETURN: '반려'
}

export default {
  name: 'Detail',
  mounted: function() {
    this.type = this.$attrs.type;
    if(this.type === 'view' || this.type === 'update'){
      this.getApproval(this.$attrs.approvalId);
    }
  },
  methods: {
    goEdit() {
      this.type = 'edit';
    },
    getApproval(id) {
      let params = {
            approvalId: id
      } 
      axios.get('/api/approval/getApproval', {params: params})
              .then(res => {
                  if(res.data.code === 0) {
                      console.log(res.data.body);
                      
                      this.approvalId = res.data.body.approvalId;
                      this.requestMemberName = res.data.body.requestMemberName;
                      this.approveMemberName = res.data.body.approveMemberName;
                      this.requestStatusCode = res.data.body.requestStatusCode;
                      this.approvalStatusCode = res.data.body.approvalStatusCode;
                      this.approvalState = REQUEST_STATUS_CODE[res.data.body.approvalStatusCode];
                      this.title = res.data.body.title;
                      this.content = res.data.body.content;
                      this.registerDate = res.data.body.registerDate;
                      this.approveDate = res.data.body.approveDate;
                  }
              });
      }, 
      setParams() {
        let params = {
          title: this.title,
          content: this.content,
          approveMemberId: 2,
          requestMemberId: 4, 
          approveMemberGradeId: 2,
          requestMemberGradeId: 4,
        }
        return params;
      },
    registApproval() {
      let registerParam = this.setParams();
      axios.post('/api/approval', registerParam)
        .then(response => {
          console.log(response);
      })
    },
    updateApproval() {
        let updateParam = this.setParams();
        updateParam.approvalId = this.approvalId;
        updateParam.requestStatusCode = this.requestStatusCode;
        updateParam.approvalStatusCode = this.approvalStatusCode;

        axios.put('/api/approval', updateParam)
          .then(response => {
            console.log(response);
          })
    },
    deleteApproval() {
      let deleteParam = {
        approvalId: this.approvalId,
        requestStatusCode: this.requestStatusCode,
        approvalStatusCode: this.approvalStatusCode
      };
        axios.delete('/api/approval', {
          data: deleteParam
        })
          .then(response => {
            console.log(response);
          })
    }
    },
    data() {
      return {
        type: 'register',
        approvalId: '',
        requestMemberName: '',
        approveMemberName: '',
        requestStatusCode: '',
        approvalState: '',
        approvalStatusCode: '',
        title: '',
        content: '',
        registerDate: '',
        approveDate: '',
      }
    }
  
}
</script>
