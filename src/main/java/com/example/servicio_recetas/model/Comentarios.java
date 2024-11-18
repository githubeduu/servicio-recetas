package com.example.servicio_recetas.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentarios")
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comentarioId;

    @Column(name = "receta_id", nullable = false)
    private Long recetaId;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "comentario", columnDefinition = "TEXT", nullable = false)
    private String comentario;

    @Column(name = "puntuacion", nullable = false)
    private int puntuacion;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = new Date(System.currentTimeMillis());
    }

    // Getters and Setters
    public Long getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(Long comentarioId) {
        this.comentarioId = comentarioId;
    }

    public Long getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
