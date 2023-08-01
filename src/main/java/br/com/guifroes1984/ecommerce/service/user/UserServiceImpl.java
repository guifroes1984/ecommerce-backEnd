package br.com.guifroes1984.ecommerce.service.user;

import br.com.guifroes1984.ecommerce.dto.InscreverDTO;
import br.com.guifroes1984.ecommerce.dto.UserDTO;
import br.com.guifroes1984.ecommerce.entities.User;
import br.com.guifroes1984.ecommerce.enums.UserRole;
import br.com.guifroes1984.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO criarUser(InscreverDTO inscreverDTO) {
        User user = new User();
        
        user.setNome(inscreverDTO.getNome());
        user.setEmail(inscreverDTO.getEmail());
        user.setUserRole(UserRole.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(inscreverDTO.getPassword()));
        User criarUser = userRepository.save(user);
        
        UserDTO userDTO = new UserDTO();
        
        userDTO.setId(criarUser.getId());
        userDTO.setNome(criarUser.getNome());
        userDTO.setEmail(criarUser.getEmail());
        userDTO.setUserRole(criarUser.getUserRole());

        return userDTO;
    }

	@Override
	public boolean temUserComEmail(String email) {
		return userRepository.findFirstByEmail(email) != null;
	}
}
