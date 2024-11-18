package com.example.servicio_recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servicio_recetas.model.Comentarios;
import com.example.servicio_recetas.repository.ComentariosRepository;

@Service
public class ComentariosLmpl implements ComentariosService {

    @Autowired
    private ComentariosRepository comentariosRepository;


    @Override
    public void crearComentario(Comentarios nuevoComentario) {
        // Guarda el comentario en la base de datos
        comentariosRepository.save(nuevoComentario);
    }

    @Override
    public List<Comentarios> obtenerComentariosPorReceta(Long recetaId) {
        return comentariosRepository.findByRecetaId(recetaId);
    }
}
