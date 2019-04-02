package tamk.tiko.BloggingWebApp;

import org.springframework.data.repository.CrudRepository;

public interface MyRepository extends CrudRepository<BlogPost, Integer> {
    Iterable<BlogPost> findBlogPostByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrNameContainingIgnoreCase(String title, String content, String name);
}