package com.restaurante.reservas_system.controller;

import com.restaurante.reservas_system.model.Reservation;
import com.restaurante.reservas_system.model.RestaurantTable;
import com.restaurante.reservas_system.repository.RestaurantTableRepository;
import com.restaurante.reservas_system.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantTableRepository tableRepository;

    @GetMapping
    public List<Reservation> getReservations () {
        return reservationService.getAllReservations();
    }

    @GetMapping(params = "name")
    public List<Reservation> getByName (@RequestParam String name) {
        return reservationService.getReservationsByName(name);
    }

    @GetMapping(params = "email")
    public List<Reservation> getByEmail (@RequestParam String email) {
        return reservationService.getReservationsByEmail(email);
    }

    @GetMapping(params = "dateTime")
    public List<Reservation> getByDateTime (@RequestParam String dateTime) {
        try {
        LocalDateTime date = LocalDateTime.parse(dateTime);
        return reservationService.getReservationsByDateTime(date);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date. Use the format: yyyy-MM-dd'T'HH:mm");
        }
    }

    @PostMapping
    public Reservation createReserve(@RequestBody Map<String, Object> requestBody) throws Exception {
        String reservedTo = (String) requestBody.get("reservedTo");
        String contactEmail = (String) requestBody.get("contactEmail");
        String dateTimeStr = (String) requestBody.get("reservationDateTime");
        Object tableNumberObj = requestBody.get("tableNumber");

        if (reservedTo == null || reservedTo.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client name is needed. Try again!");
        }
        if (contactEmail == null || contactEmail.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A valid email is needed. Try again!");
        }
        if (dateTimeStr == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date and hour are needed. Try again!");
        }

        LocalDateTime reservationDateTime;
        try {
            reservationDateTime = LocalDateTime.parse(dateTimeStr);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date. Use the format: yyyy-MM-dd'T'HH:mm");
        }

        Long tableNumber;
        if (tableNumberObj instanceof Integer) {
            tableNumber = ((Integer) tableNumberObj).longValue();
        } else if (tableNumberObj instanceof Long) {
            tableNumber = (Long) tableNumberObj;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Table number must be a number.");
        }

        if (tableNumber == null || tableNumber <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A valid table number is needed. Try again!");
        }

        RestaurantTable table = tableRepository.findByNumber(tableNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Table with number " + tableNumber + " not found."));

        Reservation reservation = new Reservation();
        reservation.setReservedTo(reservedTo);
        reservation.setContactEmail(contactEmail);
        reservation.setReservationDateTime(reservationDateTime);
        reservation.setReservedTable(table);

        return reservationService.createReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable long id, @RequestBody Map<String, Object> requestBody) throws Exception {
        String reservedTo = (String) requestBody.get("reservedTo");
        String contactEmail = (String) requestBody.get("contactEmail");
        String dateTimeStr = (String) requestBody.get("reservationDateTime");

        Object tableIdObj = requestBody.get("reservedTableId");
        Long reservationTableId;
        if (tableIdObj instanceof Integer) {
            reservationTableId = ((Integer) tableIdObj).longValue();
        } else if (tableIdObj instanceof Long) {
            reservationTableId = (Long) tableIdObj;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Table ID must be a number.");
        }

        if (reservedTo == null || reservedTo.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client name is needed. Try again!");
        }

        if (contactEmail == null || contactEmail.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A valid email is needed. Try again!");
        }

        LocalDateTime reservationDateTime;
        try {
            reservationDateTime = LocalDateTime.parse(dateTimeStr);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date. Use the format: yyyy-MM-dd'T'HH:mm");
        }

        RestaurantTable table = tableRepository.findById(reservationTableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Table not found. Try again!"));


        Reservation reserve = new Reservation();
        reserve.setReservedTo(reservedTo);
        reserve.setContactEmail(contactEmail);
        reserve.setReservationDateTime(reservationDateTime);


        reserve.setReservedTable(table);
        reserve.setId(id);
        return reservationService.updateReservation(reserve);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable long id) throws Exception {
        reservationService.deleteReservation(id);
    }

}
