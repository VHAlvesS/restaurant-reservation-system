package com.restaurante.reservas_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reservedTo;
    private LocalDateTime reservationDateTime;
    private String contactEmail;

    @ManyToOne
    @JoinColumn(name = "table_id")
    @JsonIgnore
    private RestaurantTable reservedTable;

    public Reservation(){}

    public Reservation(String reservedTo, LocalDateTime reservationDateTime, String contactEmail, RestaurantTable reservedTable) {
        this.reservedTo = reservedTo;
        this.reservationDateTime = reservationDateTime;
        this.contactEmail = contactEmail;
        this.reservedTable = reservedTable;
    }

    @Override
    public String toString() {
        return "Reservation{id=" + id + ", reservedTo='" + reservedTo + "', reservationDateTime=" + reservationDateTime + "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(String reservedTo) {
        this.reservedTo = reservedTo;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public RestaurantTable getReservedTable() {
        return reservedTable;
    }

    public void setReservedTable(RestaurantTable reservedTable) {
        this.reservedTable = reservedTable;
    }
}
