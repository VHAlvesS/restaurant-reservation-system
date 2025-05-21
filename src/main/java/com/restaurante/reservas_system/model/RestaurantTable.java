package com.restaurante.reservas_system.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RestaurantTable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int number;

    private int capacity;

    @OneToMany(mappedBy = "reservedTable")
    private List<Reservation> reservations = new ArrayList<>();

    public RestaurantTable() {}

    public RestaurantTable(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
