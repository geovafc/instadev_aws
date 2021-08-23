package br.com.instadev.aws_instadev.repository;

import br.com.instadev.aws_instadev.model.Feed;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FeedRepository extends CrudRepository<Feed, Long> {

    Optional<Feed> findByTitle(String title);
}
