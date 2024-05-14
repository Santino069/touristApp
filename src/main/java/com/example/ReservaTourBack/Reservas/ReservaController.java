package com.example.ReservaTourBack.Reservas;

import com.example.ReservaTourBack.Tours.ToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(path = "/reserva")
public class ReservaController {

    @Autowired
    private ToursService toursService;

    @GetMapping("/clientes")
    public ResponseEntity<List<Reserva>> obtenerReserva() {
        List<Reserva> reservas = toursService.obtenerClientes();
        if (!reservas.isEmpty()) {
            return ResponseEntity.ok(reservas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
