package com.example.servicio_recetas.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.servicio_recetas.model.RecetaDetalle;
import com.example.servicio_recetas.model.RecetaMedia;
import com.example.servicio_recetas.model.Recetas;


public interface RecetasService {
    List<Recetas> getAllRecetas();
    Optional<Recetas> getRecetaById(Long id); 
    Optional<RecetaDetalle> getDetalleById(Long id);
    void saveMedia(Long recetaId, String tipo, MultipartFile file) throws IOException;
    Optional<RecetaMedia> getMediaById(Long mediaId);
    List<RecetaMedia> getMediaByRecetaId(Long recetaId);
    void saveYoutubeLink(Long recetaId, String youtubeUrl);

}
