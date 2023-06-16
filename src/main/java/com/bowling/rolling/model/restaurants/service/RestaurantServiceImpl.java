package com.bowling.rolling.model.restaurants.service;

import com.bowling.rolling.model.restaurants.domain.RepresentativeMenu;
import com.bowling.rolling.model.restaurants.domain.Restaurant;
import com.bowling.rolling.model.restaurants.repository.RepresentativeMenuRepository;
import com.bowling.rolling.model.restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RepresentativeMenuRepository representativeMenuRepository;


    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public boolean deleteRestaurantById(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean addRepresentativeMenu(Long restaurantId, List<RepresentativeMenu> representativeMenuList) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant != null) {
            for (RepresentativeMenu representativeMenu : representativeMenuList) {
                representativeMenu.setRestaurant(restaurant);
                restaurant.getRepresentativeMenuList().add(representativeMenu);
                representativeMenuRepository.save(representativeMenu);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRepresentativeMenu(Long representativeMenuId) {
        if (representativeMenuRepository.existsById(representativeMenuId)) {
            representativeMenuRepository.deleteById(representativeMenuId);
            return true;
        } else {
            return false;
        }
    }


}
