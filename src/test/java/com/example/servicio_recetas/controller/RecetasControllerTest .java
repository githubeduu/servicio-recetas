package com.example.servicio_recetas.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.example.servicio_recetas.model.Recetas;
import com.example.servicio_recetas.service.RecetasService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

class RecetasControllerTest {

    @Mock
    private RecetasService recetasService;

    @InjectMocks
    private RecetasController recetasController;

    public RecetasControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecetaById() {
        // Arrange
        Long recetaId = 1L;
        Recetas mockReceta = new Recetas();
        mockReceta.setRecetaId(recetaId);
        mockReceta.setNombre("Receta Prueba");
        when(recetasService.getRecetaById(recetaId)).thenReturn(Optional.of(mockReceta));

        // Act
        ResponseEntity<Recetas> response = recetasController.getReceta(recetaId);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(recetaId, response.getBody().getRecetaId());
    }
}
