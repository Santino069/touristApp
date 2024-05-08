package com.example.ReservaTourBack.Tours;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table
public class Tours {

    @Id
    @SequenceGenerator(
            name = "tours_sequence",
            sequenceName = "tours_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tours_sequence"
    )

    private Long id;
    private String nombre;
    @Column(columnDefinition = "text")
    private String descripcion;
    private String ciudad;
    private long precio;
    private String hora_recogida;
    private String lugar_recogida;

    public Tours(Long id, String nombre, String descripcion, String ciudad, int precio, String hora_recogida, String lugar_recogida) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.precio = precio;
        this.hora_recogida = hora_recogida;
        this.lugar_recogida = lugar_recogida;
    }

    public Tours(String nombre, String descripcion, String ciudad, int precio, String hora_recogida, String lugar_recogida) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.precio = precio;
        this.hora_recogida = hora_recogida;
        this.lugar_recogida = lugar_recogida;
    }

    public Tours(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public long getPrecio() {
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

    public String getLugar_recogida() {
        return lugar_recogida;
    }

    public void setLugar_recogida(String lugar_recogida) {
        this.lugar_recogida = lugar_recogida;
    }

    @Override
    public String toString() {
        return "Tours{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", precio=" + precio +
                ", hora_recogida='" + hora_recogida + '\'' +
                ", lugar_recogida='" + lugar_recogida + '\'' +
                '}';
    }
}
