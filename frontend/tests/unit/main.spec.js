// import { expect } from 'chai'
import { shallowMount, createLocalVue } from '@vue/test-utils';
import MainPage from '@/components/view/main/Main.vue'
import Vuex from 'vuex';

const localVue = createLocalVue();
localVue.use(Vuex);

describe('Main.vue', () => {
  let store;
  let wrapper;
  let loginMember = {
    member: {
      memberId: 1,
      name: '라이언',
      gradeId: 1,
      gradeName: '회장'
    }
  }

  beforeEach(() => {
    store = new Vuex.Store({
      state: loginMember
    });
    wrapper = shallowMount(MainPage, { store, localVue });
  });


  it('로그인정보가없는가', () => {
    console.log('test start')
    console.log(store.state.member.name);
    console.log(wrapper.vm);
  })
})
