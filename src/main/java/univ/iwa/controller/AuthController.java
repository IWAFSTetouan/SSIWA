package univ.iwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.iwa.model.AuthRequest;
import univ.iwa.utils.JwtUtil;

@RestController
public class AuthController {
@Autowired AuthenticationManager authenticationManager;
@Autowired UserDetailsService userDetailsService;
@Autowired JwtUtil jwtUtil;
	@PostMapping("/authenticate")
	public String createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
	authenticationManager.authenticate(
	new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
	return jwtUtil.generateToken(userDetails);
	}

}
