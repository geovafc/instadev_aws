package br.com.instadev.aws_instadev.controller;


import br.com.instadev.aws_instadev.model.Post;
import br.com.instadev.aws_instadev.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
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
            @RequestBody Post post) {
        Post postCreated = postRepository.save(post);

        return new ResponseEntity<Post>(postCreated,
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Post> updatePost(
            @RequestBody Post post, @PathVariable("id") long id) {
        if (postRepository.existsById(id)) {
            post.setId(id);

            Post postUpdated = postRepository.save(post);

            return new ResponseEntity<Post>(postUpdated,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isPresent()) {
            Post post = optPost.get();

            postRepository.delete(post);

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
