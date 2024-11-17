package com.example.servicio_recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.servicio_recetas.model.Recetas;

public interface RecetasRepository extends JpaRepository<Recetas, Long> {
}