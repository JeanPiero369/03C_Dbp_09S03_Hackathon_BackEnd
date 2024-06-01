package com.example.hackathon.ListaDeReproduccion.domain;

import com.example.hackathon.Cancion.domain.Cancion;
import com.example.hackathon.Exceptions.NotFoundException;
import com.example.hackathon.ListaDeReproduccion.infrastructure.ListaDeReproduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaDeReproduccionService {
    @Autowired
    ListaDeReproduccionRepository listaDeReproduccionRepository;

    public List<Cancion> getSongs(Long id) {
        return listaDeReproduccionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Playlist not found")
        ).getCanciones();
    }

    public String addSong(Long id, Cancion cancion) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Playlist not found")
        );
        listaDeReproduccion.addCancion(cancion);
        ListaDeReproduccion aux = listaDeReproduccionRepository.save(listaDeReproduccion);
        return "/playlists/" + aux.getId() + "/songs/" + cancion.getId();
    }

    public void deleteSong(Long id, Long songId) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Playlist not found")
        );
        listaDeReproduccion.deleteCancion(songId);
        listaDeReproduccionRepository.save(listaDeReproduccion);
    }



}
