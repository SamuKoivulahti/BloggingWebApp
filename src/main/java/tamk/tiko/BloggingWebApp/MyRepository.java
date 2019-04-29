package tamk.tiko.BloggingWebApp;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface for blog management.
 *
 * @author Joni Alanko <joni.alanko@tuni.fi>
 *         Samu Koivulahti <samu.koivulahti@tuni.fi>
 * @version 20190429
 * @since   1.8
 */
public interface MyRepository extends CrudRepository<BlogPost, Integer> {

    /**
     * Finds all blog posts containing given parameters in title, content or author of post.
     *
     * @param title String contained in title.
     * @param content String contained in content.
     * @param name String contained in authors name.
     * @return Blog posts found with given parameters.
     */
    Iterable<BlogPost> findBlogPostByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrNameContainingIgnoreCase(String title, String content, String name);
}