package br.com.instadev.aws_instadev.controller;


import br.com.instadev.aws_instadev.enums.EventType;
import br.com.instadev.aws_instadev.model.Post;
import br.com.instadev.aws_instadev.repository.PostRepository;
import br.com.instadev.aws_instadev.service.PostPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostRepository postRepository;
    private PostPublisher postPublisher;

    @Autowired
    public PostController(PostRepository postRepository, PostPublisher postPublisher) {
        this.postRepository = postRepository;
        this.postPublisher = postPublisher;
    }

    @GetMapping
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isPresent()) {
            return new ResponseEntity<Post>(optPost.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Post> savePost(
            @RequestBody Post post) throws JsonProcessingException {
        Post postCreated = postRepository.save(post);

        postPublisher.publishPostEvent(postCreated, EventType.POST_CREATED, "geofcmr");

        return new ResponseEntity<Post>(postCreated,
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Post> updatePost(
            @RequestBody Post post, @PathVariable("id") long id) throws JsonProcessingException {
        if (postRepository.existsById(id)) {
            post.setId(id);

            Post postUpdated = postRepository.save(post);

            postPublisher.publishPostEvent(postUpdated, EventType.POST_UPDATE, "pepe");

            return new ResponseEntity<Post>(postUpdated,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") long id) throws JsonProcessingException {
        Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isPresent()) {
            Post post = optPost.get();

            postRepository.delete(post);

            postPublisher.publishPostEvent(post, EventType.POST_DELETED, "lulu");

            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/title")
    public ResponseEntity<Post> findByTitle(@RequestParam String title) {
        Optional<Post> optPost = postRepository.findByTitle(title);
        if (optPost.isPresent()) {
            return new ResponseEntity<Post>(optPost.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }














}
