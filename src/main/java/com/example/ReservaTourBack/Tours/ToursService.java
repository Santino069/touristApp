package com.example.ReservaTourBack.Tours;

import com.example.ReservaTourBack.Reservas.Reserva;
import com.example.ReservaTourBack.Reservas.ReservaAceptada;
import com.example.ReservaTourBack.Reservas.ReservaAceptadaRepository;
import com.example.ReservaTourBack.Reservas.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Tours> searchTours(String ciudad, int precio, String hora_recogida) {
        return toursRepository.findAll().stream()
                .filter(tour -> tour.getCiudad().equalsIgnoreCase(ciudad)
                        && tour.getPrecio() <= precio
                        && tour.getHora_recogida().equals(hora_recogida))
                .collect(Collectors.toList());
    }

    public List<String> getHorasByCiudad(String ciudad) {
        return toursRepository.findAll().stream()
                .filter(tour -> tour.getCiudad().equalsIgnoreCase(ciudad))
                .map(Tours::getHora_recogida)
                .distinct()
                .collect(Collectors.toList());
    }

    //GET CIUDADDES
    public List<String> obtenerCiudades() {
        return toursRepository.findAll().stream()
                .map(Tours::getCiudad)
                .distinct()
                .collect(Collectors.toList());
    }

    //GET HORA

    public List<String> obtenerHoras() {
        return toursRepository.findAll().stream()
                .map(Tours::getHora_recogida)
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

    public Tours updateTour(Long id, Tours updatedTour) {
        return toursRepository.findById(id)
                .map(tour -> {
                    tour.setNombre(updatedTour.getNombre());
                    tour.setDescripcion(updatedTour.getDescripcion());
                    tour.setCiudad(updatedTour.getCiudad());
                    tour.setPrecio(updatedTour.getPrecio());
                    tour.setHora_recogida(updatedTour.getHora_recogida());
                    tour.setLugar_recogida(updatedTour.getLugar_recogida());
                    return toursRepository.save(tour);
                })
                .orElseThrow(() -> new IllegalArgumentException("El tour con ID " + id + " no existe."));
    }

    public List<Tours> getAllTours() {
        return toursRepository.findAll();
    }

    public List<ReservaAceptada> findReservasByDateRange(String startDate, String endDate) {
        return reservaAceptadaRepository.findAllByFechaTourBetween(startDate, endDate);
    }

}
