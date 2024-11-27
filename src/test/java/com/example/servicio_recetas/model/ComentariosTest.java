package com.example.servicio_recetas.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ComentariosTest {

    @Test
    void testGettersAndSetters() {
        Comentarios comentarios = new Comentarios();

        // Setear valores
        comentarios.setComentarioId(1L);
        comentarios.setRecetaId(100L);
        comentarios.setUsuario("TestUser");
        comentarios.setComentario("This is a test comment.");
        comentarios.setPuntuacion(5);
        Date fecha = new Date(System.currentTimeMillis());
        comentarios.setFechaCreacion(fecha);

        // Validar valores
        assertEquals(1L, comentarios.getComentarioId());
        assertEquals(100L, comentarios.getRecetaId());
        assertEquals("TestUser", comentarios.getUsuario());
        assertEquals("This is a test comment.", comentarios.getComentario());
        assertEquals(5, comentarios.getPuntuacion());
        assertEquals(fecha, comentarios.getFechaCreacion());
    }

    @Test
    void testPrePersist() {
        Comentarios comentarios = new Comentarios();

        // Invocar método @PrePersist manualmente
        comentarios.onCreate();

        // Validar que la fecha de creación no es nula
        assertNotNull(comentarios.getFechaCreacion());
    }

    @Test
    void testConstructorAndDefaultValues() {
        // Crear instancia sin establecer valores
        Comentarios comentarios = new Comentarios();

        // Validar que los valores iniciales son nulos o por defecto
        assertNull(comentarios.getComentarioId());
        assertNull(comentarios.getRecetaId());
        assertNull(comentarios.getUsuario());
        assertNull(comentarios.getComentario());
        assertEquals(0, comentarios.getPuntuacion()); // Por defecto 0 para primitivos
        assertNull(comentarios.getFechaCreacion());
    }
}
