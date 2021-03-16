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
            <span title='요청자'> 죠르디</span>
          </div>
          <div class='date'> 
            <span title='요청일자'> 2020.03.11</span>
          </div>
        </li>
        <li>
          <div class='type'> 
            <span>승인자</span>
          </div>
          <div class='name'> 
            <span title='요청자'> 라이언</span>
          </div>
          <div class='date'> 
            <span title='요청일자'> 2020.03.11</span>
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
                        <input type="text" title="카드명 입력란" class="ipt_txt" placeholder="" style="width:200px">
                    </div></td>
                </tr>
                <tr>
                    <th scope="col"><div class="th">내용</div></th>
                    <td><div class="td">
                        <textarea name="" cols="20" rows="3" class="ipt_txt" style="width:100%;height:56px"></textarea>
                    </div></td>
                </tr>
                </tbody>
            </table>
        </div>
    <div>
      <button @click="$router.push({path: 'main'})">목록으로</button>
    </div>
  </div>

</template>


<script>

export default {
  name: 'approvalFormat',
  data() {
    return {
      
    }
  },
  method: {
  }
}
</script>
