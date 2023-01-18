package com.spring.security.service;


import com.spring.security.DTO.AuthenticationRequestDTO;
import com.spring.security.DTO.AuthenticationResponseDTO;
import com.spring.security.DTO.RegisterRequestDTO;
import com.spring.security.entity.Role_Enum;
import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;
import com.spring.security.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class UserService  {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private UserRepository userRepository;
    public AuthenticationResponseDTO register(RegisterRequestDTO request) throws Exception {

        Optional<User> u = userRepository.findByEmail(request.getEmail());

        if(u.isPresent()){
            throw new Exception("Email existe deja");
        }else {
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

            System.out.println(user.getRole());
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponseDTO.builder()
                    .token(jwtToken)
                    .email(user.getEmail())
                    .build();
        }
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .email(user.getEmail())
                .token(jwtToken)
                .build();
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }
}
