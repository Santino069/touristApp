package com.example.ReservaTourBack.Reservas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaAceptadaRepository extends JpaRepository<ReservaAceptada, Long> {

    List<ReservaAceptada> findAllByFechaTourBetween(String startDate, String endDate);
}
