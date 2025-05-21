package com.restaurante.reservas_system.repository;

import com.restaurante.reservas_system.model.Reservation;
import com.restaurante.reservas_system.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByContactEmail(String email);
    List<Reservation> findByReservedTo(String reservedTo);
    List<Reservation> findByReservationDateTime(LocalDateTime reservationDateTime);
    List<Reservation> findByReservationDateTimeAndReservedTable(LocalDateTime reservationDateTime, RestaurantTable reservedTable);
}
