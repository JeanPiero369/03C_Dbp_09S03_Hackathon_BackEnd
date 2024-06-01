package com.example.hackathon.Cancion.infrastructure;

import com.example.hackathon.Cancion.domain.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
}
