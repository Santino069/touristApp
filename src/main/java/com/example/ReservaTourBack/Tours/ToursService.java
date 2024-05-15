package com.example.ReservaTourBack.Tours;

import com.example.ReservaTourBack.Reservas.Reserva;
import com.example.ReservaTourBack.Reservas.ReservaAceptada;
import com.example.ReservaTourBack.Reservas.ReservaAceptadaRepository;
import com.example.ReservaTourBack.Reservas.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToursService {
    private final ToursRepository toursRepository;
    private final ReservaRepository reservaRepository;

    @Autowired
    private ReservaAceptadaRepository reservaAceptadaRepository;

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

    //GET CIUDADDES
    public List<String> obtenerCiudades() {
        return toursRepository.findAll().stream()
                .map(Tours::getCiudad)
                .distinct()
                .collect(Collectors.toList());
    }

    //GUARDAR RESERVA - POST
    public void guardarReserva(Reserva reserva) {

        reservaRepository.save(reserva);
    }

    //GET CLIENTES
    public List<Reserva> obtenerClientes() {
        return reservaRepository.findAll();
    }

    //DELETE CLIENTES - RECHAZAR RESERVA
    public boolean deleteReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    //DELETE CLIENTES - ACEPTAR Y MOVER RESERVA
    public boolean moverYEliminarReserva(Long id) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();

            // Crear una nueva ReservaAceptada con los datos de Reserva
            ReservaAceptada reservaAceptada = new ReservaAceptada();
            reservaAceptada.setFechaTour(reserva.getFechaTour());
            reservaAceptada.setNombre(reserva.getNombre());
            reservaAceptada.setApellidos(reserva.getApellidos());
            reservaAceptada.setEmail(reserva.getEmail());
            reservaAceptada.setTelefono(reserva.getTelefono());
            reservaAceptada.setNumAdultos(reserva.getNumAdultos());
            reservaAceptada.setNumMenores(reserva.getNumMenores());
            reservaAceptada.setMedioContacto(reserva.getMedioContacto());
            reservaAceptada.setComentarios(reserva.getComentarios());
            reservaAceptada.setTourSeleccionado(reserva.getTourSeleccionado());
            reservaAceptada.setCiudad(reserva.getCiudad());
            reservaAceptada.setNumeroCedula(reserva.getNumeroCedula());
            reservaAceptada.setMetodoPago(reserva.getMetodoPago());

            // Guardar en la tabla ReservaAceptada
            reservaAceptadaRepository.save(reservaAceptada);

            // Eliminar de la tabla Reserva
            reservaRepository.deleteById(id);

            return true;
        } else {
            return false;
        }
    }

}
