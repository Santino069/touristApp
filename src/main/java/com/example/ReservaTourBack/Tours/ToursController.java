package com.example.ReservaTourBack.Tours;

import com.example.ReservaTourBack.Reservas.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tours")
public class ToursController {

    private final ToursService toursService;

    @Autowired
    public ToursController(ToursService toursService) {
        this.toursService = toursService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<Tours>> searchTours(@RequestBody SearchCriteria criteria) {
        List<Tours> tours = toursService.searchTours(criteria.getCiudad(), criteria.getPrecio());
        return ResponseEntity.ok(tours);
    }

    @PostMapping("/reservas")
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva) {
        toursService.guardarReserva(reserva);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ciudades")
    public ResponseEntity<List<String>> getCiudades() {
        List<String> ciudades = toursService.obtenerCiudades();
        return ResponseEntity.ok(ciudades);
    }

    static class SearchCriteria {
        private String ciudad;
        private int precio;

        // Getters y setters
        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public int getPrecio() {
            return precio;
        }

        public void setPrecio(int precio) {
            this.precio = precio;
        }
    }

}
