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
        List<Tours> tours = toursService.searchTours(criteria.getCiudad(), criteria.getPrecio(), criteria.getHora_recogida());
        System.out.println("Tours encontrados: " + tours.size());
        System.out.println(criteria.getCiudad());
        System.out.println(criteria.getHora_recogida());
        System.out.println(criteria.getPrecio());
        return ResponseEntity.ok(tours);
    }

    @GetMapping("/horas/{ciudad}")
    public ResponseEntity<List<String>> getHorasByCiudad(@PathVariable String ciudad) {
        List<String> horas = toursService.getHorasByCiudad(ciudad);
        return ResponseEntity.ok(horas);
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

    @GetMapping("/horas")
    public ResponseEntity<List<String>> getHoras_recogida() {
        List<String> horas_recogida = toursService.obtenerHoras();
        return ResponseEntity.ok(horas_recogida);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Tours> updateTour(@PathVariable Long id, @RequestBody Tours updatedTour) {
        Tours tour = toursService.updateTour(id, updatedTour);
        return ResponseEntity.ok(tour);
    }
    @GetMapping
    public ResponseEntity<List<Tours>> getAllTours() {
        List<Tours> tours = toursService.getAllTours();
        return ResponseEntity.ok(tours);
    }



    static class SearchCriteria {
        private String ciudad;
        private int precio;
        private String hora_recogida;

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

        public String getHora_recogida() {
            return hora_recogida;
        }

        public void setHora_recogida(String hora_recogida) {
            this.hora_recogida = hora_recogida;
        }
    }

}
