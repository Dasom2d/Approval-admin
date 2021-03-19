import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
  uid: '',
  errorState: '',
  isAuth: false,
  memberInfo: {
    memberId: 3,
    name: '네오',
    gradeId: 3
  }
}

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions
})