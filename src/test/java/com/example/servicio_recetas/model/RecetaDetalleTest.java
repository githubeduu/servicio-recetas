package com.example.servicio_recetas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetaDetalleTest {

    @Test
    void testGettersAndSetters() {
        RecetaDetalle detalle = new RecetaDetalle();
        Recetas receta = new Recetas();
        receta.setRecetaId(1L);
        receta.setNombre("Receta Test");

        // Setear valores
        detalle.setDetalleId(1L);
        detalle.setIngredientes("Harina, Azúcar, Huevos");
        detalle.setInstrucciones("Mezclar los ingredientes y hornear");
        detalle.setTiempoCoccion("30 minutos");
        detalle.setDificultad("Fácil");
        detalle.setReceta(receta);

        // Validar valores
        assertEquals(1L, detalle.getDetalleId());
        assertEquals("Harina, Azúcar, Huevos", detalle.getIngredientes());
        assertEquals("Mezclar los ingredientes y hornear", detalle.getInstrucciones());
        assertEquals("30 minutos", detalle.getTiempoCoccion());
        assertEquals("Fácil", detalle.getDificultad());
        assertNotNull(detalle.getReceta());
        assertEquals("Receta Test", detalle.getReceta().getNombre());
    }

    @Test
    void testConstructorAndDefaultValues() {
        // Crear instancia sin establecer valores
        RecetaDetalle detalle = new RecetaDetalle();

        // Validar que los valores iniciales son nulos o por defecto
        assertNull(detalle.getDetalleId());
        assertNull(detalle.getIngredientes());
        assertNull(detalle.getInstrucciones());
        assertNull(detalle.getTiempoCoccion());
        assertNull(detalle.getDificultad());
        assertNull(detalle.getReceta());
    }
}
