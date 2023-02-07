package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.AuthRequest;
import com.shopleech.publicapi.bll.dto.AuthResponse;
import com.shopleech.publicapi.bll.dto.RegisterRequest;
import com.shopleech.base.config.Role;
import com.shopleech.publicapi.dal.mapper.UserDALMapper;
import com.shopleech.publicapi.dal.repository.UserRepository;
import com.shopleech.publicapi.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository repository;

   private final PasswordEncoder passwordEncoder;

   private final JwtService jwtService;

   private final AuthenticationManager authenticationManager;

   private final UserDALMapper mapper;

   public AuthResponse register(RegisterRequest request) {
      var user = User.builder()
              .firstname(request.getFirstname())
              .lastname(request.getLastname())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .role(Role.USER)
              .build();
      repository.addUser(mapper.mapToDto(user));

      return AuthResponse.builder()
              .token(jwtService.generateToken(user)).build();
   }

   public AuthResponse authenticate(AuthRequest request) {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
      );
      var user = repository.findByEmail(request.getEmail())
              .orElseThrow(() -> new UsernameNotFoundException("user not found"));

      return AuthResponse.builder()
              .token(jwtService.generateToken(user)).build();
   }
}
