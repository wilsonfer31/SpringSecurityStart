package com.spring.security.controlleur;

import com.spring.security.DTO.AuthenticationRequestDTO;
import com.spring.security.DTO.AuthenticationResponseDTO;
import com.spring.security.DTO.RegisterRequestDTO;
import com.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDTO> register(
      @RequestBody RegisterRequestDTO request
  ) throws Exception {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponseDTO> authenticate(
      @RequestBody AuthenticationRequestDTO request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
