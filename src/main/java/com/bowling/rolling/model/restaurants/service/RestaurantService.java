package com.bowling.rolling.model.restaurants.service;

import com.bowling.rolling.model.restaurants.domain.RepresentativeMenu;
import com.bowling.rolling.model.restaurants.domain.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(Long id);
    Restaurant updateRestaurant(Restaurant restaurant);
    boolean deleteRestaurantById(Long id);
    boolean addRepresentativeMenu(Long restaurantId, List<RepresentativeMenu> representativeMenuList);
    boolean deleteRepresentativeMenu(Long restaurantId);
}
