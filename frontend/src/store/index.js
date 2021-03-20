
import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

import loginMember from './loginMemberInfo'

const modules = {
  loginMember,
};

const plugins = [
  createPersistedState({
    paths: [
      'loginMember',
    ]
  })
];

Vue.use(Vuex);

export default new Vuex.Store({
  modules,
  plugins
});