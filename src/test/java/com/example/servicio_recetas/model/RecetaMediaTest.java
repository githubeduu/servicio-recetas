package com.example.servicio_recetas.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecetaMediaTest {

    @Test
    void testGettersAndSetters() {
        RecetaMedia media = new RecetaMedia();
        byte[] contenido = "Contenido de prueba".getBytes();
        Date fecha = new Date(System.currentTimeMillis());

        // Setear valores
        media.setMediaId(1L);
        media.setRecetaId(2L);
        media.setTipo("imagen");
        media.setContenido(contenido);
        media.setYoutubeUrl("https://www.youtube.com/embed/example");
        media.setEsPortada("S");
        media.setFechaSubida(fecha);

        // Validar valores
        assertEquals(1L, media.getMediaId());
        assertEquals(2L, media.getRecetaId());
        assertEquals("imagen", media.getTipo());
        assertArrayEquals(contenido, media.getContenido());
        assertEquals("https://www.youtube.com/embed/example", media.getYoutubeUrl());
        assertEquals("S", media.getEsPortada());
        assertEquals(fecha, media.getFechaSubida());
    }

    @Test
    void testOnCreate() {
        RecetaMedia media = new RecetaMedia();
        media.onCreate(); // Llamar al método `@PrePersist`

        assertNotNull(media.getFechaSubida());
    }

    @Test
    void testConstructorAndDefaultValues() {
        // Crear instancia sin establecer valores
        RecetaMedia media = new RecetaMedia();

        // Validar que los valores iniciales son nulos o por defecto
        assertNull(media.getMediaId());
        assertNull(media.getRecetaId());
        assertNull(media.getTipo());
        assertNull(media.getContenido());
        assertNull(media.getYoutubeUrl());
        assertNull(media.getEsPortada());
        assertNull(media.getFechaSubida());
    }
}
