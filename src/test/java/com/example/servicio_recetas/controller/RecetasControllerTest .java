package com.example.servicio_recetas.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.example.servicio_recetas.DTO.ComentarioDTO;
import com.example.servicio_recetas.model.Comentarios;
import com.example.servicio_recetas.model.RecetaDetalle;
import com.example.servicio_recetas.model.RecetaMedia;
import com.example.servicio_recetas.model.Recetas;
import com.example.servicio_recetas.service.ComentariosService;
import com.example.servicio_recetas.service.RecetasService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class RecetasControllerTest {

    @Mock
    private RecetasService recetasService;

    @Mock
    private ComentariosService comentariosService;

    @InjectMocks
    private RecetasController recetasController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecetaById_Found() {
        Long recetaId = 1L;
        Recetas mockReceta = new Recetas();
        mockReceta.setRecetaId(recetaId);
        mockReceta.setNombre("Receta Prueba");
        when(recetasService.getRecetaById(recetaId)).thenReturn(Optional.of(mockReceta));

        ResponseEntity<Recetas> response = recetasController.getReceta(recetaId);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(recetaId, response.getBody().getRecetaId());
    }

    @Test
    void testGetRecetaById_NotFound() {
        Long recetaId = 1L;
        when(recetasService.getRecetaById(recetaId)).thenReturn(Optional.empty());

        ResponseEntity<Recetas> response = recetasController.getReceta(recetaId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllRecetas() {
        Recetas receta1 = new Recetas();
        receta1.setRecetaId(1L);
        Recetas receta2 = new Recetas();
        receta2.setRecetaId(2L);

        when(recetasService.getAllRecetas()).thenReturn(List.of(receta1, receta2));

        ResponseEntity<List<Recetas>> response = recetasController.getAllRecetas();

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetRecetaDetalle_Found() {
        Long recetaId = 1L;
        RecetaDetalle mockDetalle = new RecetaDetalle();
        mockDetalle.setDetalleId(1L);
        when(recetasService.getDetalleById(recetaId)).thenReturn(Optional.of(mockDetalle));

        RecetaDetalle response = recetasController.getRecetaDetalle(recetaId);

        assertNotNull(response);
        assertEquals(1L, response.getDetalleId());
    }

    @Test
    void testGetRecetaDetalle_NotFound() {
        Long recetaId = 1L;
        when(recetasService.getDetalleById(recetaId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            recetasController.getRecetaDetalle(recetaId);
        });

        assertEquals("Receta no encontrada con el ID: " + recetaId, exception.getMessage());
    }

    @Test
    void testUploadMedia_Foto() {
        Long recetaId = 1L;
        MultipartFile mockFile = mock(MultipartFile.class);

        try {
            ResponseEntity<String> response = recetasController.uploadMedia(recetaId, "foto", mockFile, null);

            assertEquals(OK, response.getStatusCode());
            assertEquals("Media subida con éxito.", response.getBody());
            verify(recetasService, times(1)).saveMedia(eq(recetaId), eq("foto"), eq(mockFile));
        } catch (IOException e) {
            fail("IOException no esperada: " + e.getMessage());
        }
    }

    

    @Test
    void testUploadMedia_YouTube() {
        Long recetaId = 1L;
        String youtubeUrl = "https://youtu.be/example";

        ResponseEntity<String> response = recetasController.uploadMedia(recetaId, "youtube", null, youtubeUrl);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Media subida con éxito.", response.getBody());
        verify(recetasService, times(1)).saveYoutubeLink(eq(recetaId), eq(youtubeUrl));
    }

    @Test
    void testObtenerMedios() {
        Long recetaId = 1L;
        RecetaMedia media1 = new RecetaMedia();
        media1.setMediaId(1L);
        media1.setTipo("foto");
        RecetaMedia media2 = new RecetaMedia();
        media2.setMediaId(2L);
        media2.setTipo("video");
        RecetaMedia media3 = new RecetaMedia();
        media3.setMediaId(3L);
        media3.setTipo("youtube");
        media3.setYoutubeUrl("https://youtu.be/example");

        when(recetasService.getMediaByRecetaId(recetaId)).thenReturn(List.of(media1, media2, media3));

        ResponseEntity<Map<String, List<String>>> response = recetasController.obtenerMedios(recetaId);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().get("imagenes").size());
        assertEquals(1, response.getBody().get("videos").size());
        assertEquals(1, response.getBody().get("youtubeLinks").size());
    }

    @Test
    void testAgregarComentario() {
        Long recetaId = 1L;
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setUsuario("Test User");
        comentarioDTO.setComentario("Delicious!");
        comentarioDTO.setPuntuacion(5);

        ResponseEntity<String> response = recetasController.agregarComentario(recetaId, comentarioDTO);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Comentario agregado exitosamente", response.getBody());
        verify(comentariosService, times(1)).crearComentario(any(Comentarios.class));
    }

    @Test
    void testObtenerComentarios() {
        Long recetaId = 1L;
        Comentarios comentario = new Comentarios();
        comentario.setComentarioId(1L);
        comentario.setUsuario("Test User");

        when(comentariosService.obtenerComentariosPorReceta(recetaId)).thenReturn(List.of(comentario));

        ResponseEntity<List<Comentarios>> response = recetasController.obtenerComentarios(recetaId);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Test User", response.getBody().get(0).getUsuario());
    }
}
