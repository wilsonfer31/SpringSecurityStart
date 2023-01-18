package com.spring.security.DTO;

import com.spring.security.entity.Role_Enum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {


  private String username;
  private String email;
  private String password;
  private Role_Enum role;
}
