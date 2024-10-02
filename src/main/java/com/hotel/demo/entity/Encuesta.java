package com.hotel.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.awt.*;
import java.util.Date;


@Entity
@Table(name="encuestas")
public class Encuesta {
    @Id //Esta anotación especifica que este campo va a ser la clave principal de la tabla en la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //Esta anotación especifica que la clave primaria sea "auto-increment"
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private Long edad;
    private Long telefono;
    private Date FIestancia;
    private Boolean Mvisita;
    private Boolean Sutilizados;
    private Boolean NSgeneral;
    private String comentarios;
    //para filtrar por nivel de satisfaccion

    public Encuesta(Long id, String nombre,String apellidos,String email,Long edad,Long telefono,Date FIestancia,Boolean Mvisita,Boolean Sutilizados,Boolean NSgeneral,String comentarios ){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
        this.FIestancia = FIestancia;
        this.Mvisita = Mvisita;
        this.Sutilizados = Sutilizados;
        this.NSgeneral = NSgeneral;
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

    public Date getFIestancia() {
        return FIestancia;
    }

    public void setFIestancia(Date FIestancia) {
        this.FIestancia = FIestancia;
    }

    public Boolean getMvisita() {
        return Mvisita;
    }

    public void setMvisita(Boolean mvisita) {
        Mvisita = mvisita;
    }

    public Boolean getSutilizados() {
        return Sutilizados;
    }

    public void setSutilizados(Boolean sutilizados) {
        Sutilizados = sutilizados;
    }

    public Boolean getNSgeneral() {
        return NSgeneral;
    }

    public void setNSgeneral(Boolean NSgeneral) {
        this.NSgeneral = NSgeneral;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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
                ", FIestancia=" + FIestancia +
                ", Mvisita=" + Mvisita +
                ", Sutilizados=" + Sutilizados +
                ", NSgeneral=" + NSgeneral +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }

}
