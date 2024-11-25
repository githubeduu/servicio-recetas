package com.example.servicio_recetas.controller;

import com.example.servicio_recetas.model.Comentarios;
import com.example.servicio_recetas.service.ComentariosService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

class ComentariosControllerTest {

    @Mock
    private ComentariosService comentariosService;

    @InjectMocks
    private RecetasController recetasController;

    public ComentariosControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerComentarios() {
        // Arrange
        Long recetaId = 1L;
        Comentarios comentario1 = new Comentarios();
        comentario1.setComentario("Delicioso!");
        Comentarios comentario2 = new Comentarios();
        comentario2.setComentario("Muy fácil de preparar.");
        List<Comentarios> mockComentarios = List.of(comentario1, comentario2);
        when(comentariosService.obtenerComentariosPorReceta(recetaId)).thenReturn(mockComentarios);

        // Act
        ResponseEntity<List<Comentarios>> response = recetasController.obtenerComentarios(recetaId);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}
