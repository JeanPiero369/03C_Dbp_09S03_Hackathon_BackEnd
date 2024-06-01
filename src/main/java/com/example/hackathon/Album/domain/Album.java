package com.example.hackathon.Album.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String nombre;
    @Column
    Date fechaDeLanzamiento;
}
