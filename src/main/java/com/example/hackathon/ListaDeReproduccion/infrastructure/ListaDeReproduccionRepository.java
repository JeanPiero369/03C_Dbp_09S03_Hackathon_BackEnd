package com.example.hackathon.ListaDeReproduccion.infrastructure;


import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaDeReproduccionRepository extends JpaRepository<ListaDeReproduccion, Long> {
    List<ListaDeReproduccion> findAllByUsuarioId(Long usuarioId);
}
