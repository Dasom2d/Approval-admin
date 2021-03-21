import { expect } from 'chai'
import { shallowMount, createLocalVue } from '@vue/test-utils';
import MainPage from '@/components/view/main/Main.vue'
import Vuex from 'vuex';

const localVue = createLocalVue();
localVue.use(Vuex);

describe('Main.vue', () => {
  let store;
  let wrapper;
  const temporaryData = {
    loginMember: {
      member: {
        memberId: 1,
        name: '라이언',
        gradeId: 1,
        gradeName: '회장'
      }
    }
  }

  beforeEach(() => {
    store = new Vuex.Store({
      state: temporaryData
    });
    wrapper = shallowMount(MainPage, { store, localVue });
  });


  it('로그인정보가존재하는가', () => {
    const expectedMemberId = store.state.loginMember.member.memberId;
    const resultMemberId = wrapper.vm.$store.state.loginMember.member.memberId;

    expect(expectedMemberId).to.equal(resultMemberId);
  })
})
