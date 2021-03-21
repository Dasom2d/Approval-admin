// import { expect } from 'chai'
import { shallowMount, createLocalVue } from '@vue/test-utils';
import DefaultPage from '@/components/view/main/contents/Default'
import Vuex from 'vuex';

const localVue = createLocalVue();
localVue.use(Vuex);

describe('Main.vue', () => {
  let store;
  let wrapper;
  const temporaryData = {
    loginMember: {
      member: {
        memberId: 3,
        name: '네오',
        gradeId: 3,
        gradeName: '대리'
      }
    }
  }

  beforeEach(() => {
    store = new Vuex.Store({
      state: temporaryData
    });
    wrapper = shallowMount(DefaultPage, { store, localVue });
  });


  it('로그인된멤버로최근문서리스트를불러오는가', () => {
    const loginMember = store.state.loginMember.member;
    console.log('tetete');
    const params = {
      requestMemberId: loginMember.memberId,
      approveMemberId: loginMember.memberId
    }
    
    console.log('조회');
    const  recentApprovalList = wrapper.vm.getRecentApprovalList(params);
     console.log(recentApprovalList);
    // let expectedMemberName = loginMember.name;
    // let resultMemberName = recentApprovalList[0].requestMemberName;

    // expect(expectedMemberName).to.equal(resultMemberName);
  })
})
