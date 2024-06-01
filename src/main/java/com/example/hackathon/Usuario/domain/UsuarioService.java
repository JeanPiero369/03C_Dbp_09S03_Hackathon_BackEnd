package com.example.hackathon.Usuario.domain;

import com.example.hackathon.Exceptions.NotFoundException;
import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackathon.ListaDeReproduccion.infrastructure.ListaDeReproduccionRepository;
import com.example.hackathon.Usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository<Usuario> usuarioRepository;

    @Autowired
    ListaDeReproduccionRepository listaDeReproduccionRepository;

    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
            if (usuario.isEmpty()) new UsernameNotFoundException("User not found");
            return (UserDetails) usuario.get();
        };
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (Usuario) usuarioRepository.findByEmail(username).get();
    }

    public List<ListaDeReproduccion> getAllPlaylists(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        return listaDeReproduccionRepository.findAllByUsuarioId(id);
    }

    public ListaDeReproduccion getPlaylist(Long id) {
        ListaDeReproduccion lista =  listaDeReproduccionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Playlist not found")
        );
        return lista;
    }

}
