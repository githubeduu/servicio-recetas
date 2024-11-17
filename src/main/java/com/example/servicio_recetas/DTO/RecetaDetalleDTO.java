package com.example.servicio_recetas.DTO;

public class RecetaDetalleDTO {
    private Long detalleId;
    private String ingredientes;
    private String instrucciones;
    private String tiempoCoccion;
    private String dificultad;

    // Constructor
    public RecetaDetalleDTO(Long detalleId, String ingredientes, String instrucciones, String tiempoCoccion, String dificultad) {
        this.detalleId = detalleId;
        this.ingredientes = ingredientes;
        this.instrucciones = instrucciones;
        this.tiempoCoccion = tiempoCoccion;
        this.dificultad = dificultad;
    }

    // Getters y Setters
    public Long getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(Long detalleId) {
        this.detalleId = detalleId;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(String tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}
