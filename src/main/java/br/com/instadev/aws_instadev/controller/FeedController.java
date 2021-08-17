package br.com.instadev.aws_instadev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feed")
public class FeedController {

    private static final Logger LOG = LoggerFactory.getLogger(FeedController.class);

    @GetMapping("/{publicationId}")
    public ResponseEntity<?> publication(@PathVariable Long publicationId ) {
        LOG.info("Feed controller - publicationId: {}", publicationId);

        return ResponseEntity.ok("PublicationId: " + publicationId);

    }

    @GetMapping("/publicacoes")
    public ResponseEntity<?> publication() {
        LOG.info("Feed controller - listando publicacoes");

        return ResponseEntity.ok("Listando publicações: ");

    }

    @GetMapping("/publicacoes2")
    public ResponseEntity<?> publication2() {
        LOG.info("Feed controller - listando publicacoes2");

        return ResponseEntity.ok("Listando publicações2: ");

    }

}
