package com.example.servicio_recetas.model;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "recetas")
public class Recetas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receta_id")
    private Long recetaId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "ingredientes", nullable = false)
    private String ingredientes;

    @Column(name = "origen", nullable = false)
    private String origen;

    @Column(name = "dificultad", nullable = false)
    private String dificultad;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    // Getters y Setters
    public Long getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // Implementación de equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recetas recetas = (Recetas) o;
        return Objects.equals(recetaId, recetas.recetaId) &&
                Objects.equals(nombre, recetas.nombre) &&
                Objects.equals(categoria, recetas.categoria) &&
                Objects.equals(ingredientes, recetas.ingredientes) &&
                Objects.equals(origen, recetas.origen) &&
                Objects.equals(dificultad, recetas.dificultad) &&
                Objects.equals(fechaCreacion, recetas.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recetaId, nombre, categoria, ingredientes, origen, dificultad, fechaCreacion);
    }

    // Implementación de toString
    @Override
    public String toString() {
        return "Recetas{" +
                "recetaId=" + recetaId +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", origen='" + origen + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
