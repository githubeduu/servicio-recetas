package com.example.servicio_recetas.model;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "receta_media")
public class RecetaMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long media_id;

    @Column(name = "receta_id", nullable = false)
    private Long recetaId;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "contenido", nullable = true)
    @Lob
    private byte[] contenido;    

    @Column(name = "es_portada", nullable = false)
    private String esPortada;

    @Column(name = "fecha_subida", nullable = false)
    private Date fechaSubida;

    @PrePersist
    protected void onCreate() {
        this.fechaSubida = new Date(System.currentTimeMillis());
    }

    // Getter and Setters
    public Long getMediaId() {
        return media_id;
    }

    public void setMediaId(Long media_id) {
        this.media_id = media_id;
    }

    public Long getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getContenido() {
        return contenido;
    }
    
    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getEsPortada() {
        return esPortada;
    }

    public void setEsPortada(String esPortada) {
        this.esPortada = esPortada;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}
