package dev.maycon.workshopmongo.repositories;

import dev.maycon.workshopmongo.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
