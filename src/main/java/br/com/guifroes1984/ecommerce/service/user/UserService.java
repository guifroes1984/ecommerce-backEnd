package br.com.guifroes1984.ecommerce.service.user;

import br.com.guifroes1984.ecommerce.dto.InscreverDTO;
import br.com.guifroes1984.ecommerce.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTO criarUser(InscreverDTO inscreverDTO);

	boolean temUserComEmail(String email);
}
