package com.bowling.rolling.model.restaurants.repository;

import com.bowling.rolling.model.restaurants.domain.RepresentativeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentativeMenuRepository extends JpaRepository<RepresentativeMenu, Long> {

}
