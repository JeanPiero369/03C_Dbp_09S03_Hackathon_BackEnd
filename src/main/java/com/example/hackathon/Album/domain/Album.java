package com.example.hackathon.Album.domain;


import com.example.hackathon.Artista.domain.Artista;
import com.example.hackathon.Cancion.domain.Cancion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String nombre;
    @Column
    Date fechaDeLanzamiento;

    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cancion> canciones = new ArrayList<>();
}
