package br.com.guifroes1984.ecommerce.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

	private String username;
	
	private String password;
	
}
