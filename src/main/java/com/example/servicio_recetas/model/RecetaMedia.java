package com.example.servicio_recetas.model;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "receta_media")
public class RecetaMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;

    @Column(name = "receta_id", nullable = false)
    private Long recetaId;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "contenido", nullable = true)
    @Lob
    private byte[] contenido;

    @Column(name = "youtube_url", nullable = true)
    @Lob
    private String youtubeUrl; // Almacena enlaces de YouTube como texto

    @Column(name = "es_portada", nullable = false)
    private String esPortada;

    @Column(name = "fecha_subida", nullable = false)
    private Date fechaSubida;

    @PrePersist
    protected void onCreate() {
        this.fechaSubida = new Date(System.currentTimeMillis());
    }

    // Getters y Setters
    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
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

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
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
