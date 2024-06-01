package com.example.hackathon.Cancion.domain;

import com.example.hackathon.Album.domain.Album;
import com.example.hackathon.Artista.domain.Artista;
import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackathon.Usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String titulo;
    @Column
    int duracion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_listaDeReproduccion")
    private ListaDeReproduccion listaDeReproduccion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_artista")
    private Artista artista;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_album")
    private Album album;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
