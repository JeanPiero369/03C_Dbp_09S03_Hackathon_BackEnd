package com.example.hackathon.Auth.dto;

import com.example.hackathon.Usuario.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RqUserRegisterDto {
    String nombre;
    String apellido;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 8, max = 16)
    private String password;
    private Role role;

}
