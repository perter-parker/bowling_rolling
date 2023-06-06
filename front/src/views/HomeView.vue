<template>
  <div>
    <h1>식당 추가</h1>
    <form @submit.prevent="addRestaurant">
      <div>
        <label for="name">식당 이름:</label>
        <input type="text" id="name" v-model="name" required />
      </div>
      <div>
        <label for="address">주소:</label>
        <input type="text" id="address" v-model="address" required />
      </div>
      <div>
        <label for="phone">연락처:</label>
        <input type="text" id="phone" v-model="phoneNumber" required />
      </div>
      <div>
        <label for="hours">운영시간:</label>
        <input type="text" id="hours" v-model="operatingHours" />
      </div>
      <div>
        <label for="image">이미지</label>
        <input type="text" id="image" v-model="mainMenuImageURL" />
      </div>
      <h2>대표 메뉴</h2>
      <div v-for="(menu, index) in representativeMenus" :key="index">
        <div>
          <label :for="'menuName-' + index">메뉴명:</label>
          <input :id="'menuName-' + index" type="text" v-model="menu.menuName" required />
        </div>
        <button type="button" @click="removeMenu(index)">Remove</button>
      </div>
      <button type="button" @click="addMenu">Add Menu</button>
      <div>
        <button type="submit">Add</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: '',
      address: '',
      phoneNumber: '',
      operatingHours: '',
      mainMenuImageURL: '',
      representativeMenus: []
    }
  },
  methods: {
    addRestaurant() {
      const restaurantData = {
        name: this.name,
        address: this.address,
        phone_number: this.phoneNumber,
        operating_hours: this.operatingHours,
        main_menu_image_url: this.mainMenuImageURL,
        representative_menus: this.representativeMenus
      }
      console.log('Restaurant Data:', restaurantData)
    },

    addMenu() {
      this.representativeMenus.push({ menuName: '' }) // 새 대표 메뉴 추가
    },

    removeMenu(index) {
      this.representativeMenus.splice(index, 1) // 대표 메뉴 제거
    }
  }
}
</script>
