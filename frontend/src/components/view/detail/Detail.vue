<template>
  <div >
      기안신청서
    <div>
      <ModalView v-if="showApproveModal" @close="showApproveModal = false">
        <SelectApproveMember :props="approveMemberInfo" @approveMember="approveMember"></SelectApproveMember>
      </ModalView>
      <button v-if="isAvailEdit" @click="showApproveModal = true">승인자 선택</button>
      <ul>
        <li>{{approvalState}}</li>
      </ul>
      <ul>
        <li>
          <div class='type'> 
            <span>요청자</span>
          </div>
          <div class='name'> 
            <span title='요청자'>{{requestMemberInfo.name}}</span>
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
            <span title='승인자'>{{approveMemberInfo.name}}</span>
          </div>
          <div class='date'> 
            <span title='승인일자'> {{approveDate}}</span>
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
      <button @click="$router.push({path: '/main'})">목록으로</button>
      <button v-if="type==='register'" @click="registApproval()">등록하기</button>
      <button v-if="isAvailEdit" @click="goEdit()">편집</button>
      <button v-if="isAvailEdit" @click="deleteApproval()">삭제</button>
      <button v-if="type==='edit'" @click="updateApproval()">저장</button>

      <button v-if="isAvailUpdate" @click="processApproval('APPROVE')">승인</button>
      <button v-if="isAvailUpdate" @click="processApproval('RETURN')">반려</button>
    </div>
  </div>

</template>


<script>
import axios from 'axios'
import SelectApproveMember from '@/components/view/detail/modal/SelectApproveMember'
import ModalView from '@/components/view/common/ModalView'

const REQUEST_STATUS_CODE = {
  REQUEST: '요청문서',
  APPROVE: '승인문서',
  RETURN: '반려문서'
}

export default {
  name: 'Detail',
  components: {
    'SelectApproveMember': SelectApproveMember,
    'ModalView': ModalView
  },
  mounted: function() {
    this.type = this.$route.name;
    if(this.type === 'view' || this.type === 'update'){
      let approvalId = this.$route.params.id;
      this.getApproval(approvalId);
    } else if(this.type === 'register') {
      this.requestMemberInfo = this.$$store.state.memberInfo;
    }
  },
  methods: {
    goEdit() {
      this.type = 'edit';
    },
    isNull: function (s) {
		if (s == "" || s == null || s == undefined || (s != null && typeof s == "object" && !Object.keys(s).length)) {
			return true;
		} else {
			return false;
		}
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
                      this.approveMemberInfo = {
                        memberId: res.data.body.approveMemberId,
                        name: res.data.body.approveMemberName,
                        gradeId: res.data.body.approveMemberGradeId
                      }
                      this.requestMemberInfo = {
                        memberId: res.data.body.requestMemberId,
                        name: res.data.body.requestMemberName,
                        gradeId: res.data.body.requestMemberGradeId
                      }
                      this.requestStatusCode = res.data.body.requestStatusCode;
                      this.approvalStatusCode = res.data.body.approvalStatusCode;
                      this.approvalState = REQUEST_STATUS_CODE[res.data.body.approvalStatusCode];
                      this.title = res.data.body.title;
                      this.content = res.data.body.content;
                      this.registerDate = res.data.body.registerDate;
                      this.approveDate = res.data.body.approveDate;

                      this.isAvailEdit = this.type ==='view' && this.requestStatusCode === 'WAIT'
                                            && this.loginedMemberInfo.memberId === res.data.body.requestMemberId;
                      this.isAvailUpdate = this.type ==='view' && this.requestStatusCode === 'WAIT' 
                                            && this.loginedMemberInfo.memberId === res.data.body.approveMemberId;
                  }
              });
      }, 
      approveMember(member, showApproveModal) {
        this.approveMemberInfo = member;
        this.showApproveModal = showApproveModal;
      },
      setParams() {
        let params = {
          title: this.title,
          content: this.content,
          approveMemberId: this.approveMemberInfo.memberId,
          requestMemberId: this.requestMemberInfo.memberId, 
          approveMemberGradeId: this.approveMemberInfo.gradeId,
          requestMemberGradeId: this.requestMemberInfo.gradeId,
        }
        return params;
      },
    validate() {
      if(this.isAvailEdit && this.requestStatusCode === 'WAIT') {
        throw "요청상태의 문서만 수정가능합니다."
      }
      if(this.isNull(this.title)) {
        throw "제목을 입력해주세요."
      }
      if(this.title.length > 45) {
        throw "제목의 길이는 45자를 넘을 수 없습니다."
      }
      if(this.isNull(this.content)) {
        throw "내용을 입력해주세요."
      }
      if(this.isNull(this.approveMemberInfo)) {
        throw "승인자가 지정되지 않았습니다."
      } 
      if(this.content.length > 3000) {
        throw "내용은 3000자를 넘을 수 없습니다.";
      }
      if(this.approveMemberInfo.gradeId == this.requestMemberInfo.gradeId) {
        throw "승인자는 요청자와 같은 직급일 수 없습니다.";
      }
      if(this.approveMemberInfo.gradeId > this.requestMemberInfo.gradeId) {
        throw "승인자는 요청자보다 직급이 높아야합니다.";
      }
      return true;
    },
    registApproval() {
      try {
        if(this.validate()) {
        let registerParam = this.setParams();
        axios.post('/api/approval', registerParam)
        .then(response => {
            if(response.data.code === 0) {
              alert('기안을 상신하였습니다.');
              window.location.href = '/view/'+response.data.approvalId;
            }
          })
          .catch(err => {
            console.log(err.response.data);
          });
        }
      } catch(e){
        alert(e);
      }
    },
    goUpdate(url, param) {
      axios.put(url, param)
            .then(response => {
              if(response.data.code === 0) {
                alert('수정하였습니다.');
              }
              window.location.href = '/view/'+this.approvalId;
            })
            .catch(err => {
              if(!this.isNull(err.response.data)){
                alert(err.response.data.message) 
              } else {
                alert(err.response.data.error);
              }
            });   
    },
    updateApproval() {
      try {  
        if(this.validate()) {
          let updateParam = this.setParams();
          updateParam.approvalId = this.approvalId;
          updateParam.requestStatusCode = this.requestStatusCode;
          updateParam.approvalStatusCode = this.approvalStatusCode;
          this.goUpdate('/api/approval/update', updateParam);
        }
      } catch(e){
        alert(e);
      }
    },
    processApproval(changedStatusCode) {
      try {  
        if(this.validate()) {
          let processParam = {
            approvalId: this.approvalId,
            requestStatusCode: this.requestStatusCode,
            approvalStatusCode: changedStatusCode
          }
          this.goUpdate('/api/approval/process', processParam);
        }
      } catch(e){
        alert(e);
      }
    },
    deleteApproval() {
      if(confirm('삭제하시겠습니까?')) {
        if(!this.isAvailEdit) {
          alert('요청상태의 문서만 수정 가능합니다.');
        } else {
            let deleteParam = {
              approvalId: this.approvalId,
              approvalStatusCode: this.approvalStatusCode
            };
            axios.delete('/api/approval', {
              data: deleteParam})
              .then(response => {
                if(response.data.code === 0) {
                  alert('기안을 삭제하였습니다.');
                }
                window.location.href = '/main';
            })
              .catch(err => {
                alert(err.response.data.message);
            });
          }
        }
      }
    },
    data() {
      return {
        loginedMemberInfo: this.$store.state.memberInfo,
        isAvailEdit: false,
        isAvailUpdate: false,
        type: 'register',
        approveMemberInfo: {},
        requestMemberInfo: {},
        showApproveModal: false,
        approvalId: '',
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
