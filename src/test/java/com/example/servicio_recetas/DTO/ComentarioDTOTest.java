package com.example.servicio_recetas.DTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComentarioDTOTest {

    @Test
    void testComentarioDTOGettersAndSetters() {
        // Crear instancia del DTO
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        // Asignar valores usando setters
        comentarioDTO.setUsuario("Test User");
        comentarioDTO.setComentario("Este es un comentario de prueba.");
        comentarioDTO.setPuntuacion(5);

        // Validar valores usando getters
        assertEquals("Test User", comentarioDTO.getUsuario());
        assertEquals("Este es un comentario de prueba.", comentarioDTO.getComentario());
        assertEquals(5, comentarioDTO.getPuntuacion());
    }
}
