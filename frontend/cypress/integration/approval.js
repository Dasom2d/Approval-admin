describe('Main Page', function() {

    let params = {
        requestMemberId: 2,
        approveMemberId: 2
    }

    it('[GET] 로그인한 사용자의 기안 목록 조회', () => {
        cy.request('GET', '/api/approval/getApprovalList?requestMemberId=2&approveMemberId=2')
            .its('body')
            .as('data')
            .then(function () {
                console.log(this.data);
            })
    })

})