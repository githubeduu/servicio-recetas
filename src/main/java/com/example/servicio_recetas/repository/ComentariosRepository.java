package com.example.servicio_recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicio_recetas.model.Comentarios;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
    List<Comentarios> findByRecetaId(Long recetaId);
}
