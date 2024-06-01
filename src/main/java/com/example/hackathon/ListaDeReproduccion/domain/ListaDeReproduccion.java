package com.example.hackathon.ListaDeReproduccion.domain;

import com.example.hackathon.Album.domain.Album;
import com.example.hackathon.Cancion.domain.Cancion;
import com.example.hackathon.Usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaDeReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String nombre;
    @Column
    Date fechaDeCreacion;


    @OneToMany(mappedBy = "listaDeReproduccion",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cancion> canciones = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "listaDeReproduccion")
    private Usuario usuario;

    public void addCancion(Cancion cancion){
        canciones.add(cancion);
    }

    public void deleteCancion(Long id){
        canciones.removeIf(cancion -> cancion.getId().equals(id));
    }
}
