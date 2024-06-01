package com.example.hackathon.Cancion.infrastructure;

import com.example.hackathon.Cancion.domain.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    Optional<Cancion> findByTituloAndArtistaId(String titulo, Long artistaId);
}