package com.hotel.demo.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name="encuestas")
public class Encuesta {
    @Id //Esta anotación especifica que este campo va a ser la clave principal de la tabla en la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //Esta anotación especifica que la clave primaria(id) sea "auto-increment"
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private Long edad;
    private Long telefono;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fiestancia;
    private String mvisita;
    private String sutilizados;
    private String nsgeneral;
    private String comentarios;

    public Encuesta(Long id, String nombre,String apellidos,String email,Long edad,Long telefono,LocalDate fiestancia,String mvisita,String sutilizados,String nsgeneral,String comentarios ){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
        this.fiestancia = fiestancia;
        this.mvisita = mvisita;
        this.sutilizados = sutilizados;
        this.nsgeneral = nsgeneral;
        this.comentarios = comentarios;
    }
    public Encuesta(){
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFiestancia() {
        return fiestancia;
    }

    public void setFiestancia(LocalDate fiestancia) {
        this.fiestancia = fiestancia;
    }

    public String getMvisita() {
        return mvisita;
    }

    public void setMvisita(String mvisita) {
        this.mvisita = mvisita;
    }

    public String getSutilizados() {
        return sutilizados;
    }

    public void setSutilizados(String sutilizados) {
        this.sutilizados = sutilizados;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getNsgeneral() {
        return nsgeneral;
    }

    public void setNsgeneral(String nsgeneral) {
        this.nsgeneral = nsgeneral;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", telefono=" + telefono +
                ", fiestancia=" + fiestancia +
                ", mvisita=" + mvisita +
                ", sutilizados=" + sutilizados +
                ", nsgeneral=" + nsgeneral +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }

}
