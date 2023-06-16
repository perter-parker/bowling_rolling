package com.bowling.rolling.model.restaurants.repository;

import com.bowling.rolling.model.restaurants.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

