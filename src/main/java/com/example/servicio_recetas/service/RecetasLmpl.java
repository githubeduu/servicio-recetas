package com.example.servicio_recetas.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.servicio_recetas.model.RecetaDetalle;
import com.example.servicio_recetas.model.RecetaMedia;
import com.example.servicio_recetas.model.Recetas;
import com.example.servicio_recetas.repository.RecetaDetalleRepository;
import com.example.servicio_recetas.repository.RecetaMediaRepository;
import com.example.servicio_recetas.repository.RecetasRepository;

@Service
public class RecetasLmpl implements RecetasService{


    @Autowired
    private RecetasRepository recetasRepository;

    @Autowired
    private RecetaDetalleRepository recetaDetalleRepository;

    @Autowired
    private RecetaMediaRepository recetaMediaRepository;

    @Override
    public List<Recetas> getAllRecetas(){
        return recetasRepository.findAll();
    }

    @Override
    public Optional<Recetas> getRecetaById(Long id) {
        return recetasRepository.findById(id); // Simplificado, ya no es necesario inicializar 'media'
    }

    @Override
    public Optional<RecetaDetalle> getDetalleById(Long id) {
        return recetaDetalleRepository.findByReceta_RecetaId(id);
    }

    @Override
    public Optional<RecetaMedia> getMediaById(Long mediaId) {
        return recetaMediaRepository.findById(mediaId);
    }


    @Override
    public void saveMedia(Long recetaId, String tipo, MultipartFile file) throws IOException {
        // Crear y configurar el objeto RecetaMedia
        RecetaMedia media = new RecetaMedia();
        media.setRecetaId(recetaId); // Asignar el ID de la receta directamente
        media.setTipo(tipo);
        media.setContenido(file.getBytes());
        media.setEsPortada("N");

        // Guardar directamente el medio
        recetaMediaRepository.save(media);
    }

    @Override
    public void saveYoutubeLink(Long recetaId, String youtubeUrl) {
        RecetaMedia media = new RecetaMedia();
        media.setRecetaId(recetaId);
        media.setTipo("youtube");
        media.setYoutubeUrl(transformarYoutubeUrl(youtubeUrl)); // Guardar enlace en la columna youtubeUrl
        media.setEsPortada("N");

        recetaMediaRepository.save(media);
    }

    @Override
    public List<RecetaMedia> getMediaByRecetaId(Long recetaId) {
        return recetaMediaRepository.findByRecetaId(recetaId);
    }

    private String transformarYoutubeUrl(String youtubeUrl) {
        if (youtubeUrl.contains("youtu.be")) {
            return youtubeUrl.replace("youtu.be/", "www.youtube.com/embed/");
        } else if (youtubeUrl.contains("youtube.com/shorts/")) {
            return youtubeUrl.replace("youtube.com/shorts/", "youtube.com/embed/");
        } else if (youtubeUrl.contains("watch?v=")) {
            return youtubeUrl.replace("watch?v=", "embed/");
        }
        return youtubeUrl; // Devuelve la URL original si no coincide
    }


}
