package com.example.hackathon.ListaDeReproduccion.application;

import com.example.hackathon.Cancion.domain.Cancion;
import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccion;
import com.example.hackathon.ListaDeReproduccion.domain.ListaDeReproduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("/playlists")
public class PlaylistController {
    @Autowired
    ListaDeReproduccionService ListaDeReproduccionService;

    @GetMapping("/playlists/{playlist_id}/songs")
    public ResponseEntity<List<Cancion>> getSongs(
            @PathVariable("playlist_id") Long id
    ){
        return ResponseEntity.ok(ListaDeReproduccionService.getSongs(id));
    }

    @PostMapping("/playlists/{playlist_id}/songs")
    public ResponseEntity<Void> addSong(
            @PathVariable("playlist_id") Long id,
            @RequestBody Cancion cancion
    ){
        String location = ListaDeReproduccionService.addSong(id, cancion);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @DeleteMapping("/playlists/{playlist_id}/songs/{song_id}")
    public ResponseEntity<Void> deleteSong(
            @PathVariable("playlist_id") Long id,
            @PathVariable("song_id") Long songId
    ){
        ListaDeReproduccionService.deleteSong(id, songId);
        return ResponseEntity.noContent().build();
    }


}
