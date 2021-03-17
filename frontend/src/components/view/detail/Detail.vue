<template>
  <div >
      기안신청서
    <div>
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
            <span title='요청자'> {{requestMemberName}}</span>
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
                        <input type="text" v-model="title" title="제목 입력란" class="ipt_txt" placeholder="" style="width:200px">
                    </div></td>
                </tr>
                <tr>
                    <th scope="col"><div class="th">내용</div></th>
                    <td><div class="td">
                        <textarea v-model="content" name="내용 입력란" cols="20" rows="3" class="ipt_txt" style="width:100%;height:56px"></textarea>
                    </div></td>
                </tr>
                </tbody>
            </table>
        </div>
    <div>
      <button @click="$router.push({path: 'main'})">목록으로</button>
      <button @click="registApproval()">등록하기</button>
    </div>
  </div>

</template>


<script>

import axios from 'axios'

export default {
  name: 'Detail',
  mounted: function() {
    this.type = this.$attrs.type;
    if(this.type === 'view' || this.type === 'update'){
      this.getApproval(this.$attrs.approvalId);
    }
  },
  methods: {
    getApproval(id) {
      let params = {
            approvalId: id
      } 
      axios.get('/api/approval/getApproval', {params: params})
              .then(res => {
                  if(res.data.code === 0) {
                      console.log(res.data.body);
                      
                      this.requestMemberName= res.data.body.requestMemberName;
                      this.approveMemberName= res.data.body.approveMemberName;
                      this.requestStatusCode= res.data.body.requestStatusCode;
                      this.approveStatusCode= res.data.body.approveStatusCode;
                      this.title= res.data.body.title;
                      this.content= res.data.body.content;
                      this.registerDate= res.data.body.registerDate;
                      this.approveDate= res.data.body.approveDate;
                  }
              });
      },
    registApproval() {
      let addParams = {
        title: this.title,
        content: this.content,
        approveMemberId: 1,
        requestMemberId: 4, 
        approveMemberGradeId: 1,
        requestMemberGradeId: 4,
      }

      axios.post('/api/approval/registerApproval', addParams)
        .then(response =>{
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
        approveStatusCode: '',
        title: '',
        content: '',
        registerDate: '',
        approveDate: '',
      }
    }
  
}
</script>
