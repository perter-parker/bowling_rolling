package com.bowling.rolling.model.restaurants.service;

import com.bowling.rolling.model.restaurants.domain.RepresentativeMenu;
import com.bowling.rolling.model.restaurants.domain.Restaurant;
import com.bowling.rolling.model.restaurants.repository.RepresentativeMenuRepository;
import com.bowling.rolling.model.restaurants.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantServiceImplTest {
    @Autowired
    private RestaurantService restaurantService;

    @Test
    @DisplayName("식당 생성 및 대표 메뉴 추가")
    @Transactional
    void testCreateRestaurantAndAddRepresentativeMenu() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Restaurant A");
        restaurant.setAddress("123 Main St");
        restaurant.setPhoneNumber("555-1234");
        restaurant.setOpeningTime(LocalTime.of(9, 0, 0));
        restaurant.setClosingTime(LocalTime.of(22, 0, 0));
        restaurant.setAmenities("와이파이");

        RepresentativeMenu menu1 = new RepresentativeMenu();
        menu1.setMenuName("Burger");
        menu1.setPrice(10);
        menu1.setMenuImageUrl("image1");

        RepresentativeMenu menu2 = new RepresentativeMenu();
        menu2.setMenuName("Pizza");
        menu2.setPrice(15);
        menu2.setMenuImageUrl("image2");

        List<RepresentativeMenu> representativeMenuList = Arrays.asList(menu1, menu2);

        // When
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        boolean addedRepresentativeMenu = restaurantService.addRepresentativeMenu(createdRestaurant.getId(), representativeMenuList);

        // Then
        assertTrue(addedRepresentativeMenu);
        Restaurant retrievedRestaurant = restaurantService.getRestaurantById(createdRestaurant.getId());
        assertNotNull(retrievedRestaurant);
//        System.out.println(retrievedRestaurant.getRepresentativeMenuList().get(0).getMenuName());
        assertEquals("Pizza", retrievedRestaurant.getRepresentativeMenuList().get(1).getMenuName());
    }

    @Test
    @DisplayName("식당 수정 및 대표 메뉴 추가")
    @Transactional
    void testUpdateRestaurantAndAddRepresentativeMenu() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant A");
        restaurant.setAddress("123 Main St");
        restaurant.setPhoneNumber("555-1234");

        RepresentativeMenu menu1 = new RepresentativeMenu();
        menu1.setMenuName("Burger");
        menu1.setPrice(10);

        RepresentativeMenu menu2 = new RepresentativeMenu();
        menu2.setMenuName("Pizza");
        menu2.setPrice(15);

        List<RepresentativeMenu> representativeMenuList = new ArrayList<RepresentativeMenu>(Arrays.asList(menu1, menu2));

        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        Restaurant updatedRestaurant = new Restaurant();
        updatedRestaurant.setId(createdRestaurant.getId());
        updatedRestaurant.setName("Updated Restaurant");
        updatedRestaurant.setAddress("456 Elm St");
        updatedRestaurant.setPhoneNumber("555-5678");

        RepresentativeMenu menu3 = new RepresentativeMenu();
        menu3.setMenuName("Pasta");
        menu3.setPrice(12);

        representativeMenuList.add(menu3);

        // When
        restaurantService.updateRestaurant(updatedRestaurant);
        boolean addedRepresentativeMenu = restaurantService.addRepresentativeMenu(createdRestaurant.getId(), representativeMenuList);

        // Then
        assertTrue(addedRepresentativeMenu);
        Restaurant retrievedRestaurant = restaurantService.getRestaurantById(createdRestaurant.getId());
        assertNotNull(retrievedRestaurant);
        assertEquals("Updated Restaurant", retrievedRestaurant.getName());
        assertEquals("456 Elm St", retrievedRestaurant.getAddress());
        assertEquals("555-5678", retrievedRestaurant.getPhoneNumber());
        assertEquals(3, retrievedRestaurant.getRepresentativeMenuList().size());
        assertEquals("Burger", retrievedRestaurant.getRepresentativeMenuList().get(0).getMenuName());
        assertEquals("Pizza", retrievedRestaurant.getRepresentativeMenuList().get(1).getMenuName());
        assertEquals("Pasta", retrievedRestaurant.getRepresentativeMenuList().get(2).getMenuName());
    }

    @Test
    @DisplayName("식당 삭제")
    void testDeleteRestaurant() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant A");
        restaurant.setAddress("123 Main St");
        restaurant.setPhoneNumber("555-1234");

        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        // When
        boolean deleted = restaurantService.deleteRestaurantById(createdRestaurant.getId());

        // Then
        assertTrue(deleted);
        Restaurant retrievedRestaurant = restaurantService.getRestaurantById(createdRestaurant.getId());
        assertNull(retrievedRestaurant);
    }
}
