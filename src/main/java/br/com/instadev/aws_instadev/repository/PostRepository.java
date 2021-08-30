package br.com.instadev.aws_instadev.repository;

import br.com.instadev.aws_instadev.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {

    Optional<Post> findByTitle(String title);
}
