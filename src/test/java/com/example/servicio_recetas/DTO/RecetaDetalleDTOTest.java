package com.example.servicio_recetas.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetaDetalleDTOTest {

    @Test
    void testRecetaDetalleDTOConstructorAndGetters() {
        // Crear instancia del DTO utilizando el constructor
        RecetaDetalleDTO recetaDetalleDTO = new RecetaDetalleDTO(
                1L, 
                "Ingredientes de prueba", 
                "Instrucciones de prueba", 
                "30 minutos", 
                "Fácil"
        );

        // Validar valores asignados por el constructor
        assertEquals(1L, recetaDetalleDTO.getDetalleId());
        assertEquals("Ingredientes de prueba", recetaDetalleDTO.getIngredientes());
        assertEquals("Instrucciones de prueba", recetaDetalleDTO.getInstrucciones());
        assertEquals("30 minutos", recetaDetalleDTO.getTiempoCoccion());
        assertEquals("Fácil", recetaDetalleDTO.getDificultad());
    }

    @Test
    void testRecetaDetalleDTOSetters() {
        // Crear instancia del DTO
        RecetaDetalleDTO recetaDetalleDTO = new RecetaDetalleDTO(null, null, null, null, null);

        // Asignar valores utilizando setters
        recetaDetalleDTO.setDetalleId(1L);
        recetaDetalleDTO.setIngredientes("Ingredientes de prueba");
        recetaDetalleDTO.setInstrucciones("Instrucciones de prueba");
        recetaDetalleDTO.setTiempoCoccion("30 minutos");
        recetaDetalleDTO.setDificultad("Fácil");

        // Validar valores asignados por los setters
        assertEquals(1L, recetaDetalleDTO.getDetalleId());
        assertEquals("Ingredientes de prueba", recetaDetalleDTO.getIngredientes());
        assertEquals("Instrucciones de prueba", recetaDetalleDTO.getInstrucciones());
        assertEquals("30 minutos", recetaDetalleDTO.getTiempoCoccion());
        assertEquals("Fácil", recetaDetalleDTO.getDificultad());
    }
}
