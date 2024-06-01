package com.example.hackathon.Usuario.application;

import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackathon.Usuario.domain.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/users/{user_id}/playlists")
    public ResponseEntity<List<ListaDeReproduccion>> getAllPlaylists(
            @PathVariable("user_id") Long id
    ){
        return ResponseEntity.ok(usuarioService.getAllPlaylists(id));
    }

    @GetMapping("/playlists/{playlist_id}")
    public ResponseEntity<ListaDeReproduccion> getPlaylist(
            @PathVariable("playlist_id") Long id
    ){
        return ResponseEntity.ok(usuarioService.getPlaylist(id));
    }

    @PostMapping("/users/{user_id}/playlists")
    public ResponseEntity<Void> createPlaylist(
            @PathVariable("user_id") Long id
    ){
        String location = usuarioService.createPlaylist(id);
        return ResponseEntity.ok().build();
    }

}
