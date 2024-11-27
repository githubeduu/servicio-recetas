package com.example.servicio_recetas.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecetasTest {

    @Test
    void testGettersAndSetters() {
        Recetas receta = new Recetas();
        Date fechaCreacion = new Date(System.currentTimeMillis());

        // Setear valores
        receta.setRecetaId(1L);
        receta.setNombre("Tarta de Manzana");
        receta.setCategoria("Postres");
        receta.setIngredientes("Manzanas, Harina, Azúcar, Mantequilla");
        receta.setOrigen("España");
        receta.setDificultad("Fácil");
        receta.setFechaCreacion(fechaCreacion);

        // Validar valores
        assertEquals(1L, receta.getRecetaId());
        assertEquals("Tarta de Manzana", receta.getNombre());
        assertEquals("Postres", receta.getCategoria());
        assertEquals("Manzanas, Harina, Azúcar, Mantequilla", receta.getIngredientes());
        assertEquals("España", receta.getOrigen());
        assertEquals("Fácil", receta.getDificultad());
        assertEquals(fechaCreacion, receta.getFechaCreacion());
    }

    @Test
    void testDefaultValues() {
        // Crear instancia sin inicializar valores
        Recetas receta = new Recetas();

        // Validar que los valores iniciales son nulos
        assertNull(receta.getRecetaId());
        assertNull(receta.getNombre());
        assertNull(receta.getCategoria());
        assertNull(receta.getIngredientes());
        assertNull(receta.getOrigen());
        assertNull(receta.getDificultad());
        assertNull(receta.getFechaCreacion());
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear dos instancias iguales
        Recetas receta1 = new Recetas();
        receta1.setRecetaId(1L);
        receta1.setNombre("Tarta de Manzana");

        Recetas receta2 = new Recetas();
        receta2.setRecetaId(1L);
        receta2.setNombre("Tarta de Manzana");

        // Validar que son iguales
        assertEquals(receta1, receta2);
        assertEquals(receta1.hashCode(), receta2.hashCode());

        // Cambiar un atributo y validar que ya no son iguales
        receta2.setNombre("Tarta de Limón");
        assertNotEquals(receta1, receta2);
        assertNotEquals(receta1.hashCode(), receta2.hashCode());
    }

    @Test
    void testToString() {
        Recetas receta = new Recetas();
        receta.setRecetaId(1L);
        receta.setNombre("Tarta de Manzana");
        receta.setCategoria("Postres");

        String expected = "Recetas{" +
                          "recetaId=1, " +
                          "nombre='Tarta de Manzana', " +
                          "categoria='Postres'}";
        assertTrue(receta.toString().contains("Tarta de Manzana"));
        assertTrue(receta.toString().contains("Postres"));
    }
}
