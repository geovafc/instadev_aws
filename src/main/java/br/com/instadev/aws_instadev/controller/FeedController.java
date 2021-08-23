package br.com.instadev.aws_instadev.controller;


import br.com.instadev.aws_instadev.model.Feed;
import br.com.instadev.aws_instadev.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/feeds")
public class FeedController {

    private FeedRepository feedRepository;

    @Autowired
    public FeedController(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @GetMapping
    public Iterable<Feed> findAll() {
        return feedRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feed> findById(@PathVariable long id) {
        Optional<Feed> optFeed = feedRepository.findById(id);
        if (optFeed.isPresent()) {
            return new ResponseEntity<Feed>(optFeed.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Feed> saveFeed(
            @RequestBody Feed feed) {
        Feed feedCreated = feedRepository.save(feed);

        return new ResponseEntity<Feed>(feedCreated,
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Feed> updateFeed(
            @RequestBody Feed feed, @PathVariable("id") long id) {
        if (feedRepository.existsById(id)) {
            feed.setId(id);

            Feed feedUpdated = feedRepository.save(feed);

            return new ResponseEntity<Feed>(feedUpdated,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Feed> deleteFeed(@PathVariable("id") long id) {
        Optional<Feed> optFeed = feedRepository.findById(id);
        if (optFeed.isPresent()) {
            Feed feed = optFeed.get();

            feedRepository.delete(feed);

            return new ResponseEntity<Feed>(feed, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/title")
    public ResponseEntity<Feed> findByTitle(@RequestParam String title) {
        Optional<Feed> optFeed = feedRepository.findByTitle(title);
        if (optFeed.isPresent()) {
            return new ResponseEntity<Feed>(optFeed.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }














}
