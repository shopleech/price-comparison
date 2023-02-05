package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.dto.AuthRequest;
import com.shopleech.publicapi.bll.dto.AuthResponse;
import com.shopleech.publicapi.domain.RegisterRequest;
import com.shopleech.publicapi.bll.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

   private final AuthService service;

   @PostMapping("/register")
   public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
      return ResponseEntity.ok(service.register(request));
   }

   @PostMapping("/authenticate")
   public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
      return ResponseEntity.ok(service.authenticate(request));
   }
}
