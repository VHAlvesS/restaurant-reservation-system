package com.restaurante.reservas_system.service;

import com.restaurante.reservas_system.model.Reservation;
import com.restaurante.reservas_system.model.RestaurantTable;
import com.restaurante.reservas_system.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) throws Exception {
        List<Reservation> existingReservations = reservationRepository
                .findByReservationDateTimeAndReservedTable(
                        reservation.getReservationDateTime(),
                        reservation.getReservedTable()
                );

        if(!existingReservations.isEmpty()) {
            throw new Exception("This table is already reserved");
        }

     return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation) throws Exception {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long reservationId) throws Exception {
        if (!reservationRepository.existsById(reservationId)) {
            throw new IllegalArgumentException("Reservation not found.");
        }

        reservationRepository.deleteById(reservationId);
    }

    public List<Reservation> getReservationsByEmail(String email) {
        return reservationRepository.findByContactEmail(email);
    }

    public List<Reservation> getReservationsByName(String name) {
        return reservationRepository.findByReservedTo(name);
    }

    public List<Reservation> getReservationsByDateTime(LocalDateTime date) {
        return reservationRepository.findByReservationDateTime(date);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

}
