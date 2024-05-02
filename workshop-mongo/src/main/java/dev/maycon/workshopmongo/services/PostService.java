package dev.maycon.workshopmongo.services;

import dev.maycon.workshopmongo.models.dto.PostDTO;
import dev.maycon.workshopmongo.models.dto.UserDTO;
import dev.maycon.workshopmongo.models.entities.Post;
import dev.maycon.workshopmongo.models.entities.User;
import dev.maycon.workshopmongo.repositories.PostRepository;
import dev.maycon.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository userRepository;

    public PostService(PostRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PostDTO findById(String id) {
        Post entity = getEntityById(id);
        return new PostDTO(entity);
    }

    private Post getEntityById(String id) {
        Optional<Post> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
