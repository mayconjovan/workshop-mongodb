package dev.maycon.workshopmongo.services;

import dev.maycon.workshopmongo.models.dto.UserDTO;
import dev.maycon.workshopmongo.models.entities.User;
import dev.maycon.workshopmongo.repositories.UserRepository;
import dev.maycon.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(String id) {
        Optional<User> user = userRepository.findById(id);
        User entity = user.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return new UserDTO(entity);
    }
}
