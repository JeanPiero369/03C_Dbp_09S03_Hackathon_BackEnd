package com.example.hackathon.Cancion.domain;

import com.example.hackathon.Cancion.infrastructure.CancionRepository;
import com.example.hackathon.Exceptions.NotFoundException;
import com.example.hackathon.Exceptions.UniqueConstraintException;
import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackathon.Usuario.domain.Usuario;
import com.example.hackathon.Usuario.domain.UsuarioService;
import com.example.hackathon.Usuario.infrastructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    @Autowired
    CancionRepository cancionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioRepository<Usuario> usuarioRepository;

    public List<Cancion> getCanciones() {
        return cancionRepository.findAll();
    }

    public String createCancion(Cancion cancion, Principal principal) throws UniqueConstraintException {
        Optional<Cancion> cancionGuardada = cancionRepository.findByTituloAndArtistaId(
                cancion.getTitulo(),
                cancion.getArtista().getId()
        );
        if (cancionGuardada.isPresent()) {
            throw new UniqueConstraintException("Cancion already exists");
        }



        ListaDeReproduccion listaDeReproduccion=cancion.getListaDeReproduccion();
        listaDeReproduccion.setFechaDeCreacion(new Date());

        Usuario usuario=usuarioRepository.findByEmail(principal.getName()).get();
        cancion.setUsuario(usuario);
        cancion.setListaDeReproduccion(listaDeReproduccion);


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
