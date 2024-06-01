package com.example.hackathon.Usuario.application;

import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackathon.Usuario.domain.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @PathVariable("user_id") Long id,
            @RequestBody ListaDeReproduccion listaDeReproduccion
    ){
        String location = usuarioService.createPlaylist(id, listaDeReproduccion);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/playlists/{playlist_id}")
    public ResponseEntity<Void> updatePlaylist(
            @PathVariable("playlist_id") Long id,
            @RequestBody ListaDeReproduccion listaDeReproduccion
    ){
        usuarioService.updatePlaylist(id, listaDeReproduccion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/playlists/{playlist_id}")
    public ResponseEntity<Void> deletePlaylist(
            @PathVariable("playlist_id") Long id
    ){
        usuarioService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

}
