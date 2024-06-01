package com.example.hackathon.ListaDeReproduccion.domain;

import com.example.hackathon.Cancion.domain.Cancion;
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


}
