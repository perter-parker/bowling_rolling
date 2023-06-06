package com.bowling.rolling.service;

import com.bowling.rolling.domain.RepresentativeMenu;
import com.bowling.rolling.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void createRestaurantTest() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
        restaurant.setPhoneNumber("123-456-7890");
        restaurant.setOperatingHours("9am - 10pm");
        restaurant.setMainMenuImageUrl("http://example.com/image.jpg");

        // When
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        // Then
        assertNotNull(createdRestaurant.getId());
        assertEquals("Test Restaurant", createdRestaurant.getName());
        assertEquals("123 Test Street", createdRestaurant.getAddress());
        assertEquals("123-456-7890", createdRestaurant.getPhoneNumber());
        assertEquals("9am - 10pm", createdRestaurant.getOperatingHours());
        assertEquals("http://example.com/image.jpg", createdRestaurant.getMainMenuImageUrl());
    }

    @Test
    void getRestaurantByIdTest() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
        restaurant.setPhoneNumber("123-456-7890");
        restaurant.setOperatingHours("9am - 10pm");
        restaurant.setMainMenuImageUrl("http://example.com/image.jpg");
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        // When
        Restaurant retrievedRestaurant = restaurantService.getRestaurantById(createdRestaurant.getId());

        // Then
        assertEquals(createdRestaurant, retrievedRestaurant);
    }

    @Test
    void updateRestaurantTest() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
        restaurant.setPhoneNumber("123-456-7890");
        restaurant.setOperatingHours("9am - 10pm");
        restaurant.setMainMenuImageUrl("http://example.com/image.jpg");
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        // When
        createdRestaurant.setName("Updated Restaurant");
        createdRestaurant.setAddress("456 Updated Street");
        createdRestaurant.setPhoneNumber("987-654-3210");
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(createdRestaurant);

        // Then
        assertEquals("Updated Restaurant", updatedRestaurant.getName());
        assertEquals("456 Updated Street", updatedRestaurant.getAddress());
        assertEquals("987-654-3210", updatedRestaurant.getPhoneNumber());
    }

    @Test
    void deleteRestaurantByIdTest() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
        restaurant.setPhoneNumber("123-456-7890");
        restaurant.setOperatingHours("9am - 10pm");
        restaurant.setMainMenuImageUrl("http://example.com/image.jpg");
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        // When
        boolean deleted = restaurantService.deleteRestaurantById(createdRestaurant.getId());

        // Then
        assertTrue(deleted);
        assertNull(restaurantService.getRestaurantById(createdRestaurant.getId()));
    }

    @Test
    void addRepresentativeMenuTest() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
        restaurant.setPhoneNumber("123-456-7890");
        restaurant.setOperatingHours("9am - 10pm");
        restaurant.setMainMenuImageUrl("http://example.com/image.jpg");
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        RepresentativeMenu menu = new RepresentativeMenu();
        menu.setMenuName("Test Menu");

        // When
        RepresentativeMenu addedMenu = restaurantService.addRepresentativeMenu(createdRestaurant.getId(), menu);
        // Then
        assertNotNull(addedMenu.getId());
        assertEquals("Test Menu", addedMenu.getMenuName());
        assertEquals(createdRestaurant.getId(), addedMenu.getRestaurant().getId());
    }

    @Test
    void deleteRepresentativeMenuTest() {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
        restaurant.setPhoneNumber("123-456-7890");
        restaurant.setOperatingHours("9am - 10pm");
        restaurant.setMainMenuImageUrl("http://example.com/image.jpg");
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        RepresentativeMenu menu1 = new RepresentativeMenu();
        menu1.setMenuName("Test Menu 1");

        RepresentativeMenu menu2 = new RepresentativeMenu();
        menu2.setMenuName("Test Menu 2");

        restaurantService.addRepresentativeMenu(createdRestaurant.getId(), menu1);
        RepresentativeMenu addedMenu = restaurantService.addRepresentativeMenu(createdRestaurant.getId(), menu2);

        // When
        boolean deleted = restaurantService.deleteRepresentativeMenu(createdRestaurant.getId(), addedMenu.getId());

        // Then
        assertTrue(deleted);
        Restaurant retrievedRestaurant = restaurantService.getRestaurantById(createdRestaurant.getId());
        List<RepresentativeMenu> representativeMenus = retrievedRestaurant.getRepresentativeMenus();
        assertEquals(1, representativeMenus.size());
        assertNotEquals(addedMenu.getId(), representativeMenus.get(0).getId());
    }
}


