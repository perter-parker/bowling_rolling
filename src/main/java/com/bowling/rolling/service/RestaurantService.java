package com.bowling.rolling.service;

import com.bowling.rolling.domain.RepresentativeMenu;
import com.bowling.rolling.domain.Restaurant;
import com.bowling.rolling.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        restaurant.setRepresentativeMenus(new ArrayList<>());
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public boolean deleteRestaurantById(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public RepresentativeMenu addRepresentativeMenu(Long restaurantId, RepresentativeMenu representativeMenu) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant != null) {
            representativeMenu.setRestaurant(restaurant);
            restaurant.getRepresentativeMenus().add(representativeMenu);
            restaurantRepository.save(restaurant);
            return representativeMenu;
        }
        return new RepresentativeMenu();
    }

    public boolean deleteRepresentativeMenu(Long restaurantId, Long representativeMenuId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant != null) {
            List<RepresentativeMenu> representativeMenus = restaurant.getRepresentativeMenus();
            representativeMenus.removeIf(menu -> menu.getId().equals(representativeMenuId));
            restaurantRepository.save(restaurant);
            return true;
        }
        return false;
    }
}
