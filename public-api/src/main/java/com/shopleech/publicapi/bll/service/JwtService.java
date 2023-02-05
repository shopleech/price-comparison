package com.shopleech.publicapi.bll.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ahto Jalak
 * @since 03.02.2023
 */
@Service
public class JwtService {

   private final String SECRET_KEY="o_0";

   public String extractUsername(String jwt) {
      return extractClaim(jwt, Claims::getSubject);
   }

   private Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   private <T> T extractClaim(String jwt, Function<Claims, T> claimsTResolver) {
      final Claims claims = extractAllClaims(jwt);
      return claimsTResolver.apply(claims);
   }

   public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
      return Jwts.builder()
              .setClaims(extraClaims)
              .setSubject(userDetails.getUsername())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
              .signWith(getSignInKey(), SignatureAlgorithm.HS256)
              .compact();
   }

   public String generateToken(UserDetails userDetails) {
      return generateToken(new HashMap<>(), userDetails);
   }

   public boolean isTokenValid(String jwt, UserDetails userDetails) {
      final String username = extractUsername(jwt);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
   }

   private boolean isTokenExpired(String jwt) {
      return extractExpiration(jwt)
              .before(new Date(System.currentTimeMillis()));
   }

   private Claims extractAllClaims(String jwt) {
      return Jwts.parserBuilder()
              .setSigningKey(getSignInKey())
              .build()
              .parseClaimsJws(jwt)
              .getBody();
   }

   private Key getSignInKey() {
      return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
   }
}
