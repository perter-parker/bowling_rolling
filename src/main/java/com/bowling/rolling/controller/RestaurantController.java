package com.bowling.rolling.controller;

import com.bowling.rolling.model.restaurants.domain.Restaurant;
import com.bowling.rolling.model.restaurants.service.RestaurantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantServiceImpl restaurantService;

    /**
     * 식당 메뉴 생성
     * @param restaurant
     * @return
     */
    @PostMapping("/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        restaurantService.addRepresentativeMenu(createdRestaurant.getId(), createdRestaurant.getRepresentativeMenuList());
        return ResponseEntity.ok(createdRestaurant);
    }

    /**
     * 식당 메뉴 읽기
     * @param id
     * @return
     */
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        System.out.println(restaurant);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 식당 메뉴 수정
     * @param id
     * @param updatedRestaurant
     * @return
     */
    @PutMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            restaurant.setName(updatedRestaurant.getName());
            restaurant.setAddress(updatedRestaurant.getAddress());
            restaurant.setPhoneNumber(updatedRestaurant.getPhoneNumber());
            restaurant.setOpeningTime(updatedRestaurant.getOpeningTime());
            restaurant.setClosingTime(updatedRestaurant.getClosingTime());
            restaurant.setAmenities(updatedRestaurant.getAmenities());
            restaurantService.addRepresentativeMenu(id, restaurant.getRepresentativeMenuList());
            Restaurant updated = restaurantService.updateRestaurant(updatedRestaurant);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 식당 메뉴 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        boolean deleted = restaurantService.deleteRestaurantById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
