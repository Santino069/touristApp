package com.example.ReservaTourBack.Tours;

import com.example.ReservaTourBack.Reservas.Reserva;
import com.example.ReservaTourBack.Reservas.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToursService {
    private final ToursRepository toursRepository;
    private final ReservaRepository reservaRepository;

    @Autowired
    public ToursService(ToursRepository toursRepository, ReservaRepository reservaRepository) {
        this.toursRepository = toursRepository;
        this.reservaRepository = reservaRepository;
    }

    public List<Tours> searchTours(String ciudad, int precio) {
        return toursRepository.findAll().stream()
                .filter(tour -> {
                    // Filtrar por ciudad y presupuesto
                    return tour.getCiudad().equalsIgnoreCase(ciudad) && tour.getPrecio() <= precio;
                })
                .collect(Collectors.toList());
    }

    public List<String> obtenerCiudades() {
        return toursRepository.findAll().stream()
                .map(Tours::getCiudad)
                .distinct()
                .collect(Collectors.toList());
    }

    public void guardarReserva(Reserva reserva) {

        reservaRepository.save(reserva);
    }

}
