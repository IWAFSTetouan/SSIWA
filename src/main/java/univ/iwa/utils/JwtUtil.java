package univ.iwa.utils;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
private Claims extractAllClaims(String token) {
return Jwts.parserBuilder().setSigningKey(key).build()
.parseClaimsJws(token).getBody();}
public String extractUsername(String token) {
Function<Claims, String> claimsResolver=Claims::getSubject;
return claimsResolver.apply(extractAllClaims(token));}
public String generateToken(UserDetails userDetails) {
return Jwts.builder()
.setSubject(userDetails.getUsername())
.setIssuedAt(new Date())
.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
.signWith(key).compact();}
public Boolean validateToken(String token, UserDetails userDetails) {
return extractUsername(token).equals(userDetails.getUsername());
}}
