package com.example.hackathon.Auth.domain;

import com.example.hackathon.Auth.dto.RpJwtAuthtentication;
import com.example.hackathon.Auth.dto.RqUserLoginDto;
import com.example.hackathon.Auth.dto.RqUserRegisterDto;
import com.example.hackathon.Exceptions.NotFoundException;
import com.example.hackathon.Exceptions.UniqueConstraintException;
import com.example.hackathon.Usuario.domain.Usuario;
import com.example.hackathon.Usuario.infrastructure.UsuarioRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    private ModelMapper modelMapper;

    public RpJwtAuthtentication login(RqUserLoginDto rqUserDto) {
        Optional<Usuario> user= usuarioRepository.findByEmail(rqUserDto.getEmail());

        if (user.isEmpty()) throw new NotFoundException("Email is not registered");
        if (!passwordEncoder.matches(rqUserDto.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        RpJwtAuthtentication token = new RpJwtAuthtentication();
        token.setToken(jwtService.generateToken(user.get()));
        return token;
    }

    public RpJwtAuthtentication register(RqUserRegisterDto usuario){
        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if (user.isPresent()) throw new UniqueConstraintException("Email is already registered");

        RpJwtAuthtentication token = new RpJwtAuthtentication();

        Usuario usuarioNuevo = modelMapper.map(usuario, Usuario.class);
        usuarioRepository.save(usuarioNuevo);
        token.setToken(jwtService.generateToken(usuarioNuevo));

        return token;
    }
}
