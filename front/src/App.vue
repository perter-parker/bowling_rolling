<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">로그인</RouterLink>
      <RouterLink to="/home">식당추가</RouterLink>
      <RouterLink to="/menu">메뉴리스트</RouterLink>
    </nav>
  </header>

  <RouterView />
</template>
<style scoped></style>
