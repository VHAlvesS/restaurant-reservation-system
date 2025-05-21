package com.restaurante.reservas_system.controller;

import com.restaurante.reservas_system.model.RestaurantTable;
import com.restaurante.reservas_system.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class RestaurantTableController {
    @Autowired
    private RestaurantTableRepository tableRepository;

    @GetMapping
    public List<RestaurantTable> getAllTables() {
        return tableRepository.findAll();
    }

    @PostMapping
    public RestaurantTable createTable(@RequestBody RestaurantTable table) {
        if(table.getNumber() <= 0) {
            throw new RuntimeException("Invalid table number.");
        }
        if(table.getCapacity() <= 0) {
            throw new RuntimeException("Table should be greater than 0.");
        }
        if (tableRepository.existsByNumber(table.getNumber())) {
            throw new RuntimeException("There's a table with this number. Try again with another number!");
        }

        return tableRepository.save(table);
    }

    @GetMapping("/{id}")
    public RestaurantTable getTableWithReservations(@PathVariable Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable Long id) {
        if (!tableRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found.");
        }
        tableRepository.deleteById(id);
    }

}
