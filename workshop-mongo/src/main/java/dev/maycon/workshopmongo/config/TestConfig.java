package dev.maycon.workshopmongo.config;

import dev.maycon.workshopmongo.models.entities.Post;
import dev.maycon.workshopmongo.models.entities.User;
import dev.maycon.workshopmongo.models.embedded.Author;
import dev.maycon.workshopmongo.models.embedded.Comment;
import dev.maycon.workshopmongo.repositories.PostRepository;
import dev.maycon.workshopmongo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void init() {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Gray", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.parse("2024-04-28T22:51:01Z"), "Partiu viagem", "Vou viajar para Santa Catarina.", new Author(alex));
        Post post2 = new Post(null, Instant.parse("2024-04-28T22:52:03Z"), "Bom dia", "Acordei feliz hoje!.", new Author(maria));

        Comment c1 = new Comment("Boa viagem mano!", Instant.parse("2024-04-28T22:54:13Z"), new Author(alex));
        Comment c2 = new Comment("Aproveite!", Instant.parse("2024-04-28T22:55:33Z"), new Author(bob));
        Comment c3 = new Comment("Tenha um bom dia!", Instant.parse("2024-04-28T22:56:23Z"), new Author(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(maria);


    }

}
