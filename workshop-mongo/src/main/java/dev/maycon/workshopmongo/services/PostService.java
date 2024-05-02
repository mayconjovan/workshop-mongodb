package dev.maycon.workshopmongo.services;

import dev.maycon.workshopmongo.models.dto.PostDTO;
import dev.maycon.workshopmongo.models.entities.Post;
import dev.maycon.workshopmongo.repositories.PostRepository;
import dev.maycon.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDTO findById(String id) {
        Post entity = getEntityById(id);
        return new PostDTO(entity);
    }

    private Post getEntityById(String id) {
        Optional<Post> user = postRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<PostDTO> findByTitle(String title) {
        List<Post> list = postRepository.searchTitle(title);
        return list.stream().map(PostDTO::new).toList();
    }

    public List<PostDTO> fullSearch(String title, String start, String end) {
        Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
        Instant endMoment = convertMoment(end, Instant.now());

        List<Post> list = postRepository.fullSearch(title, startMoment, endMoment);
        return list.stream().map(PostDTO::new).toList();
    }

    private Instant convertMoment(String original, Instant alternative) {
        try {
            return Instant.parse(original);
        } catch (DateTimeParseException e) {
            return alternative;
        }
    }

}
