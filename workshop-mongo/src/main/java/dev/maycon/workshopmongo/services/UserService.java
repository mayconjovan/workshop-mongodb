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

    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(String id) {
        User entity = getEntityById(id);

        return new UserDTO(entity);
    }

    public UserDTO insert(UserDTO userDTO) {
        User entity = new User();
        copyDtoToEntity(userDTO, entity);
        entity = userRepository.insert(entity);
        return new UserDTO(entity);
    }

    public UserDTO update(String id, UserDTO userDTO) {
        User entity = getEntityById(id);
        copyDtoToEntity(userDTO, entity);
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    public void delete(String id) {

    }

    private User getEntityById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private void copyDtoToEntity(UserDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }

}
