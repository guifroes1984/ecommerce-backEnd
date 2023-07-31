package br.com.guifroes1984.ecommerce.controller;

import br.com.guifroes1984.ecommerce.dto.InscreverDTO;
import br.com.guifroes1984.ecommerce.dto.UserDTO;
import br.com.guifroes1984.ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InscreverController {

    @Autowired
    private UserService userService;

    @PostMapping("/inscrever-se")
    public ResponseEntity<?> inscreverUser(@RequestBody InscreverDTO inscreverDTO) {
    	
    	if (userService.temUserComEmail(inscreverDTO.getEmail())) {
			return new ResponseEntity<>("O Usuário já existe!", HttpStatus.NOT_ACCEPTABLE);
		}
    	
        UserDTO criadoUser = userService.criarUser(inscreverDTO);
        if (criadoUser == null) {
            return new ResponseEntity<>("Usuário não criado. Volte mais tarde!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(criadoUser, HttpStatus.CREATED);

    }

}
