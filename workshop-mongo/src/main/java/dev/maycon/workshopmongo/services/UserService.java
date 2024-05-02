package dev.maycon.workshopmongo.services;

import dev.maycon.workshopmongo.models.dto.UserDTO;
import dev.maycon.workshopmongo.models.entities.User;
import dev.maycon.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }
}
