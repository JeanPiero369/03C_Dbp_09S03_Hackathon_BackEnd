package com.example.hackathon.Usuario.domain;

import com.example.hackathon.Exceptions.NotFoundException;
import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackathon.ListaDeReproduccion.infrastructure.ListaDeReproduccionRepository;
import com.example.hackathon.Usuario.infrastructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

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

    public String createPlaylist(Long id, ListaDeReproduccion lista){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        lista.setUsuario(usuario);
        listaDeReproduccionRepository.save(lista);
        return "/playlists/" + lista.getId();
    }

    public void updatePlaylist(Long id, ListaDeReproduccion lista){
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Playlist not found")
        );
        modelMapper.map(listaDeReproduccion, lista);
        listaDeReproduccionRepository.save(listaDeReproduccion);
    }

    public void deletePlaylist(Long id){
        ListaDeReproduccion lista = listaDeReproduccionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Playlist not found")
        );
        listaDeReproduccionRepository.deleteById(id);
    }
}
