const initialState = {
    member: {}
};
  
  const mutations = {
    LOGIN(state, value) {
      state.member = value;
    },
    LOGOUT(state) {
      state.member = {};
    }
  }; 
  
  export default {
    strict: process.env.NODE_ENV !== 'production',
    namespaced: true,
    state: {
      ...initialState
    },
    mutations
  }