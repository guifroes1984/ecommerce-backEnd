package br.com.guifroes1984.ecommerce.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.guifroes1984.ecommerce.dto.AuthenticationRequest;
import br.com.guifroes1984.ecommerce.dto.AuthenticationResponse;
import br.com.guifroes1984.ecommerce.entities.User;
import br.com.guifroes1984.ecommerce.repository.UserRepository;
import br.com.guifroes1984.ecommerce.service.user.UserService;
import br.com.guifroes1984.ecommerce.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/autenticar")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Usuário ou senha incorretos.");
		} catch (DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "O usuário não está ativado");
			return null;
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());
		return new AuthenticationResponse(jwt);
	}
	
}
