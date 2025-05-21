package com.restaurante.reservas_system.repository;

import com.restaurante.reservas_system.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    boolean existsByNumber(int number);
    Optional<RestaurantTable> findById(Long id);
    Optional<RestaurantTable> findByNumber(Long number);
}
