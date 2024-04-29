package dev.maycon.workshopmongo.repositories;

import dev.maycon.workshopmongo.models.entities.Post;
import dev.maycon.workshopmongo.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
