package com.example.servicio_recetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicio_recetas.model.RecetaDetalle;

@Repository
public interface RecetaDetalleRepository extends JpaRepository<RecetaDetalle, Long> {
    Optional<RecetaDetalle> findByReceta_RecetaId(Long recetaId);
}