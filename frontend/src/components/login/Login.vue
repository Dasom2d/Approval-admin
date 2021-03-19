// src/components/Login.vue
<template>
  <div>
      <h2>Log In</h2>
      <form @submit.prevent="goSubmit">
          <input placeholder="Enter your ID" v-model="uid">
          <input placeholder="Enter your password" v-model="password">
          <button type="submit">Login</button>
      </form>

      <form>
      </form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: 'Login',
  methods: {
    ...mapActions(['login']),
    async onSubmit () {
      try {
        let loginResult = await this.login({uid: this.uid, password: this.password})
        console.log(loginResult) // 로그인 성공하면 true, 아니면 false
      } catch (err) {
        console.error(err)
      }
    },
    goSubmit() {
          let form = new FormData();
          form.append('username', this.uid);
          form.append('password', this.password);

          return axios.post('/loginProcess', form);
    }
  },
  data: () => ({
    uid: '',
    password: ''
  }),
}
</script>

<style>

</style>