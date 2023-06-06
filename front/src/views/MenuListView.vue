<template>
  <div>
    <h1>Restaurant Menu List</h1>
    <ul>
      <li v-for="menu in menus" :key="menu.id">
        <h3>{{ menu.menuName }}</h3>
        <p>{{ menu.restaurantName }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menus: [] // 식당 메뉴 배열
    }
  },
  mounted() {
    // 식당 메뉴 데이터를 서버로부터 가져오는 메서드 호출
    this.fetchMenus()
  },
  methods: {
    fetchMenus() {
      // 식당 메뉴 데이터를 서버로부터 가져오는 로직을 구현
      this.menus = [
        { id: 1, menuName: 'Menu 1', restaurantName: 'Restaurant A' },
        { id: 2, menuName: 'Menu 2', restaurantName: 'Restaurant B' },
        { id: 3, menuName: 'Menu 3', restaurantName: 'Restaurant C' }
      ]
    }
  }
}
</script>
