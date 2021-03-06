// test/test.js



describe('Example', function() {
    describe('add 함수 테스트', function() {
        it('1+2를 넣으면 3의 값이 나와야 한다.', function(done) {
            var sum = add(1,2);
            if(sum == 3) {
                done();
            } else {
                throw Error('3의 값이 나오지 않았어요.');
            }
        });

        it('2+2를 넣으면 5의 값이 나와야 한다.', function(done) { // 인위적 에러발생
            
            var sum = add(2,2);
            expect(sum).to.equal(5);

            // var sum = add(2,2);
            // if(sum == 5) {
            //     done();
            // } else {
            //     throw Error('5의 값이 나오지 않았어요.');
            // }
        });

        it('조회가 이루어져야한다.', function(done) {
            var list = approval();
            console.log(list);
        })
    });
});