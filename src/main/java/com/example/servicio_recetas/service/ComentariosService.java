package com.example.servicio_recetas.service;

import java.util.List;
import com.example.servicio_recetas.model.Comentarios;

public interface ComentariosService {
    
    void crearComentario(Comentarios nuevoComentario);
    List<Comentarios> obtenerComentariosPorReceta(Long recetaId);
}
