package com.example.hackathon.Artista.infrastructure;


import com.example.hackathon.Artista.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

}
