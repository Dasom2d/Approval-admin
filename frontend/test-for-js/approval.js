// test/add.js

module.exports = approval;


const request = async (fetchUrl, options) => {
    try{
        $loading.style.display = 'block';
        const res = await fetch(fetchUrl, {
            headers: {
                'Content-Type': 'application/json'
            },
            ...options
        });
    
        if(res.ok){
            $loading.style.display = 'none';
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




async function  approval() {
    console.log('t');
    try{
        const res = await fetch('https://localhost:3000/api/approval/getApprovalList?requestMemberId=2&approveMemberId=2', {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader(
          "Access-Control-Allow-Methods",
          "OPTIONS, GET, POST, PUT, PATCH, DELETE" // what matters here is that OPTIONS is present
        );
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    
        if(res.ok){
            $loading.style.display = 'none';
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