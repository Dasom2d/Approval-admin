// test/add.js

module.exports = approval;


// CORS 정책에 위반하여 실패
async function  approval() {
    try{
        const res = await fetch('http://localhost:3000/api/approval/getApprovalList?requestMemberId=2&approveMemberId=2', {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if(res.ok){
            console.log(res.json());
            return await res.json();
        } else {
        new Promise((resolve, reject) => {
            throw new Error(`조회 중 에러가 발생하였습니다.`);
          }).catch(alert);  
        }
    } catch(e) {
        new Promise((resolve, reject) => {
            throw new Error(`서버 통신 중 에러가 발생하였습니다. ; ${e.message}`);
          }).catch(alert);        
    }
}