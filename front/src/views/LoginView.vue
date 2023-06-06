<template>
  <div>
    <h1>로그인</h1>
    <form @submit.prevent="login">
      <div>
        <label for="username">아이디:</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <button type="submit">로그인</button>
    </form>
  </div>
</template>

<script lang="ts">
import axios from 'axios'

export default {
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    async login() {
      const reponse = await axios.post('/api/login', {
        username: this.username,
        password: this.password
      })

      console.log(reponse.data)
    }
  }
}
</script>
