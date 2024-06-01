package com.example.hackathon.Cancion.application;

import com.example.hackathon.Cancion.domain.Cancion;
import com.example.hackathon.Cancion.domain.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/canciones")
public class CancionController {

    @Autowired
    CancionService cancionesService;

    @GetMapping
    public ResponseEntity<List<Cancion>> getCanciones() {
        return ResponseEntity.ok(cancionesService.getCanciones());
    }

    @PostMapping
    public ResponseEntity<Void> createCancion(
            @RequestBody Cancion cancion
    ){
        String location = cancionesService.createCancion(cancion);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCancion(
            @PathVariable("id") Long id,
            @RequestBody Cancion cancion
    ){
        cancionesService.updateCancion(id, cancion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCancion(
            @PathVariable("id") Long id
    ){
        cancionesService.deleteCancion(id);
        return ResponseEntity.noContent().build();
    }
}
