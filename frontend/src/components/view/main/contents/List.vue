<template>
    <div id="list">
        <div id="content">
            <div class="section_tit frst">
                <h3>완료문서<span class="tit_dsc"> 총 8 건</span>
                </h3>
                <table id="approval-list">
                    <thead>
                        <tr>
                            <th scope="col">문서번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">기안자</th>
                            <th scope="col">기안일</th>
                            <th scope="col">완료일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                                <td>
                                    <router-link to="/view"> te</router-link>
                                </td>
                            <td>죠르디</td>
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

export default {
  name: 'List',
  components: {
  },
  data() {
    return {
    }
  }
}
</script>
