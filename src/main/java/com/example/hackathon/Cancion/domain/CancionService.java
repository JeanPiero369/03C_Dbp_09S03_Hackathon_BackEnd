package com.example.hackathon.Cancion.domain;

import com.example.hackathon.Cancion.infrastructure.CancionRepository;
import com.example.hackathon.Exceptions.NotFoundException;
import com.example.hackathon.Exceptions.UniqueConstraintException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    @Autowired
    CancionRepository cancionRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Cancion> getCanciones() {
        return cancionRepository.findAll();
    }

    public String createCancion(Cancion cancion) {
        Optional<Cancion> cancionGuardada = cancionRepository.findByTituloAndArtistaId(
                cancion.getTitulo(),
                cancion.getArtista().getId()
        );
        if (cancionGuardada.isPresent()) {
            throw new UniqueConstraintException("Cancion already exists");
        }
        Cancion savedCancion = cancionRepository.save(cancion);
        return "/canciones/" + savedCancion.getId();
    }

    public void updateCancion(Long id, Cancion cancion){
        Cancion cancionGuardada = cancionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Cancion not found")

        );
        modelMapper.map(cancionGuardada, cancion);
    }

    public void deleteCancion(Long id){
        Cancion cancionGuardada = cancionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Cancion not found")
        );
        cancionRepository.delete(cancionGuardada);
    }

}
