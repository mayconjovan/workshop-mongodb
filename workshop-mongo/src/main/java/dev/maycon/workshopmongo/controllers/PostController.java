package dev.maycon.workshopmongo.controllers;

import dev.maycon.workshopmongo.models.dto.PostDTO;
import dev.maycon.workshopmongo.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable String id) {
        PostDTO post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }


}
