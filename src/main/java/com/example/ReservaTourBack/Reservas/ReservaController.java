package com.example.ReservaTourBack.Reservas;

import com.example.ReservaTourBack.Tours.ToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/reserva")
public class ReservaController {

    @Autowired
    private ToursService toursService;

    //OBTENER CLIENTES
    @GetMapping("/clientes")
    public ResponseEntity<List<Reserva>> obtenerReserva() {
        List<Reserva> reservas = toursService.obtenerClientes();
        if (!reservas.isEmpty()) {
            return ResponseEntity.ok(reservas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //BORRAR - RECHAZAR
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        boolean deleted = toursService.deleteReserva(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //BORRAR Y PASAR - ACEPTAR
    @DeleteMapping("/clientes/{id}/mover")
    public ResponseEntity<Void> moverYEliminarReserva(@PathVariable Long id) {
        boolean moved = toursService.moverYEliminarReserva(id);
        if (moved) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // BUSCAR RESERVAS POR RANGO DE FECHAS
    @PostMapping("/buscar")
    public ResponseEntity<List<ReservaAceptada>> buscarReservasPorFechas(@RequestBody RangoFechasRequest request) {
        List<ReservaAceptada> reservas = toursService.findReservasByDateRange(request.getStartDate(), request.getEndDate());
        return ResponseEntity.ok(reservas);
    }

}

class RangoFechasRequest {
    private String startDate;
    private String endDate;

    // Getters y setters
    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }


}