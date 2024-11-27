package com.example.servicio_recetas.services;

import com.example.servicio_recetas.model.Comentarios;
import com.example.servicio_recetas.repository.ComentariosRepository;
import com.example.servicio_recetas.service.ComentariosLmpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ComentariosLmplTest {

    @Mock
    private ComentariosRepository comentariosRepository;

    @InjectMocks
    private ComentariosLmpl comentariosService; // Usa la clase concreta en lugar de la interfaz

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearComentario() {
        // Configuración
        Comentarios nuevoComentario = new Comentarios();
        nuevoComentario.setComentarioId(1L); // ID se corresponde con comentarioId
        nuevoComentario.setRecetaId(1L);
        nuevoComentario.setUsuario("Andre Miranda");
        nuevoComentario.setComentario("No me gustó la receta.");
        nuevoComentario.setPuntuacion(2);

        // Actuar
        comentariosService.crearComentario(nuevoComentario);

        // Verificar
        verify(comentariosRepository, times(1)).save(nuevoComentario);
    }

    @Test
    void testObtenerComentariosPorReceta() {
        // Configuración
        Long recetaId = 1L;

        Comentarios comentario1 = new Comentarios();
        comentario1.setComentarioId(1L); // ID se corresponde con comentarioId
        comentario1.setRecetaId(recetaId);
        comentario1.setUsuario("Usuario 1");
        comentario1.setComentario("Comentario 1");
        comentario1.setPuntuacion(4);

        Comentarios comentario2 = new Comentarios();
        comentario2.setComentarioId(2L);
        comentario2.setRecetaId(recetaId);
        comentario2.setUsuario("Usuario 2");
        comentario2.setComentario("Comentario 2");
        comentario2.setPuntuacion(5);

        List<Comentarios> comentariosMock = Arrays.asList(comentario1, comentario2);

        // Mockear el comportamiento del repositorio
        when(comentariosRepository.findByRecetaId(recetaId)).thenReturn(comentariosMock);

        // Actuar
        List<Comentarios> comentarios = comentariosService.obtenerComentariosPorReceta(recetaId);

        // Verificar
        assertEquals(2, comentarios.size());
        assertEquals("Comentario 1", comentarios.get(0).getComentario());
        assertEquals("Usuario 1", comentarios.get(0).getUsuario());
        assertEquals("Comentario 2", comentarios.get(1).getComentario());
        assertEquals("Usuario 2", comentarios.get(1).getUsuario());

        verify(comentariosRepository, times(1)).findByRecetaId(recetaId);
    }
}
