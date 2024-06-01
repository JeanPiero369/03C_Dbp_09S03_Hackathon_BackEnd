package com.example.hackathon.ListaDeReproduccion.infrastructure;


import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeReproduccionRepository extends JpaRepository<ListaDeReproduccion, Long> {
}
