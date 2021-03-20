import Vue from 'vue'
import Vuex from 'vuex'
// import createPersistedState from "vuex-persistedstate"
// import Member from '@/memberInfo'

Vue.use(Vuex)

export default new Vuex.Store({
  // modules: {Member},
  // plugins: [createPersistedState({
    // paths: ["Member"]
  // })],
  state: {
    memberInfo: {}
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
