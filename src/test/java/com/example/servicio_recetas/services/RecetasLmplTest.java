package com.example.servicio_recetas.services;

import com.example.servicio_recetas.model.RecetaDetalle;
import com.example.servicio_recetas.model.RecetaMedia;
import com.example.servicio_recetas.model.Recetas;
import com.example.servicio_recetas.repository.RecetaDetalleRepository;
import com.example.servicio_recetas.repository.RecetaMediaRepository;
import com.example.servicio_recetas.repository.RecetasRepository;
import com.example.servicio_recetas.service.RecetasLmpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecetasLmplTest {

    @InjectMocks
    private RecetasLmpl recetasService;

    @Mock
    private RecetasRepository recetasRepository;

    @Mock
    private RecetaDetalleRepository recetaDetalleRepository;

    @Mock
    private RecetaMediaRepository recetaMediaRepository;

    @Mock
    private MultipartFile mockFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRecetas() {
        Recetas receta = new Recetas();
        receta.setRecetaId(1L);
        receta.setNombre("Receta Test");
        when(recetasRepository.findAll()).thenReturn(List.of(receta));

        List<Recetas> recetas = recetasService.getAllRecetas();

        assertNotNull(recetas);
        assertEquals(1, recetas.size());
        assertEquals("Receta Test", recetas.get(0).getNombre());

        verify(recetasRepository, times(1)).findAll();
    }

    @Test
    void testGetRecetaById() {
        Recetas receta = new Recetas();
        receta.setRecetaId(1L);
        receta.setNombre("Receta Test");
        when(recetasRepository.findById(1L)).thenReturn(Optional.of(receta));

        Optional<Recetas> result = recetasService.getRecetaById(1L);

        assertTrue(result.isPresent());
        assertEquals("Receta Test", result.get().getNombre());

        verify(recetasRepository, times(1)).findById(1L);
    }

    @Test
    void testGetDetalleById() {
        RecetaDetalle detalle = new RecetaDetalle();
        detalle.setDetalleId(1L);
        detalle.setTiempoCoccion("30 minutos");
        when(recetaDetalleRepository.findByReceta_RecetaId(1L)).thenReturn(Optional.of(detalle));

        Optional<RecetaDetalle> result = recetasService.getDetalleById(1L);

        assertTrue(result.isPresent());
        assertEquals("30 minutos", result.get().getTiempoCoccion());

        verify(recetaDetalleRepository, times(1)).findByReceta_RecetaId(1L);
    }

    @Test
    void testSaveMedia() throws IOException {
        when(mockFile.getBytes()).thenReturn("Contenido de prueba".getBytes());

        recetasService.saveMedia(1L, "imagen", mockFile);

        verify(recetaMediaRepository, times(1)).save(any(RecetaMedia.class));
    }

    @Test
    void testSaveYoutubeLink() {
        String youtubeUrl = "https://youtu.be/example";

        recetasService.saveYoutubeLink(1L, youtubeUrl);

        verify(recetaMediaRepository, times(1)).save(any(RecetaMedia.class));
    }

    @Test
    void testGetMediaByRecetaId() {
        RecetaMedia media = new RecetaMedia();
        media.setRecetaId(1L);
        media.setTipo("imagen");
        when(recetaMediaRepository.findByRecetaId(1L)).thenReturn(List.of(media));

        List<RecetaMedia> result = recetasService.getMediaByRecetaId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("imagen", result.get(0).getTipo());

        verify(recetaMediaRepository, times(1)).findByRecetaId(1L);
    }

    @Test
    void testTransformarYoutubeUrl() throws Exception {
        Method method = RecetasLmpl.class.getDeclaredMethod("transformarYoutubeUrl", String.class);
        method.setAccessible(true);

        String youtubeShortUrl = "https://youtu.be/example";
        String transformedUrl = (String) method.invoke(recetasService, youtubeShortUrl);

        assertEquals("https://www.youtube.com/embed/example", transformedUrl);
    }

    @Test
    void testGetMediaById() {
        RecetaMedia media = new RecetaMedia();
        media.setMediaId(1L);
        media.setRecetaId(1L);
        media.setTipo("imagen");
        media.setContenido("Contenido de prueba".getBytes());

        when(recetaMediaRepository.findById(1L)).thenReturn(Optional.of(media));

        Optional<RecetaMedia> result = recetasService.getMediaById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getMediaId());
        assertEquals("imagen", result.get().getTipo());
        verify(recetaMediaRepository, times(1)).findById(1L);
    }

}
