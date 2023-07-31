package br.com.guifroes1984.ecommerce.dto;

import br.com.guifroes1984.ecommerce.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String nome;

    private String email;

    private String password;

    private UserRole userRole;

    
}
