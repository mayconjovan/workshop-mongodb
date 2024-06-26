package dev.maycon.workshopmongo.controllers;

import dev.maycon.workshopmongo.models.dto.PostDTO;
import dev.maycon.workshopmongo.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        List<PostDTO> post = service.findByTitle(text);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<PostDTO>> findByFullText(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "title", defaultValue = "") String start,
            @RequestParam(value = "end", defaultValue = "") String end) {
        List<PostDTO> post = service.fullSearch(text, start, end);
        return ResponseEntity.ok().body(post);
    }

}
