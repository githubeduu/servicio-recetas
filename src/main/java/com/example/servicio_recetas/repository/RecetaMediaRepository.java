package com.example.servicio_recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicio_recetas.model.RecetaMedia;

@Repository
public interface RecetaMediaRepository extends JpaRepository<RecetaMedia, Long> {
    List<RecetaMedia> findByRecetaId(Long recetaId);
}
