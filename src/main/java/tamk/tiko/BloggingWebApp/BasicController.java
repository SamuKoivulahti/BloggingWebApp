package tamk.tiko.BloggingWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * Controller class for blog data.
 *
 * @author Joni Alanko <joni.alanko@tuni.fi>
 *         Samu Koivulahti <samu.koivulahti@tuni.fi>
 * @version 20190429
 * @since   1.8
 */
@RestController
public class BasicController {

    /**
     * Repository for accessing blog database.
     */
    @Autowired
    MyRepository repository;

    /**
     * Returns all blog posts containing given parameter in posts title, content or author name.
     *
     * @param value String to search blog posts with.
     * @return All blog posts containing given parameter.
     */
    @GetMapping("blogs/search/{value}")
    public Iterable<BlogPost> searchBlogPost(@PathVariable String value) {
        return repository.findBlogPostByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrNameContainingIgnoreCase(value, value, value);
    }

    /**
     * Returns blog post with id given in URL.
     *
     * @param id Id of blog to return.
     * @return Blog post with given Id.
     */
    @GetMapping("/blogs/{id:\\d}")
    public BlogPost getBlogPost(@PathVariable int id) {

        return repository.findById(id).orElseThrow(() -> new NotFoundException(id, "BlogPost " + id + " not found"));
    }

    /**
     * Returns all blog posts.
     *
     * @return All blog posts.
     */
    @GetMapping("/blogs")
    public Iterable<BlogPost> getAllBlogPosts() {
        return repository.findAll();
    }

    /**
     * Deletes blog post with id given in URL.
     *
     * @param id Id of blog to delete.
     * @return Status NO_CONTENT if successful.
     */
    @DeleteMapping("/blogs/{id:\\d}")
    public ResponseEntity<Void> removeBlogPost(@PathVariable int id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Adds blog post to database.
     *
     * @param user Author of blog post.
     * @param title Title of blog post.
     * @param content Content of blog post.
     * @param builder Used to build header of response.
     * @return Status CREATED if successful.
     */
    @PostMapping("/blogs")
    public ResponseEntity<Void> addBlogPost(@RequestParam String user, @RequestParam String title, @RequestParam String content, UriComponentsBuilder builder) {
        BlogPost blogPost = new BlogPost(user, title, content);
        repository.save(blogPost);

        return getVoidResponseEntity(builder, blogPost, HttpStatus.CREATED);
    }

    /**
     * Gets proper response.
     *
     * @param builder Used to build header of response.
     * @param blogPost Blog post to respond with.
     * @param status HttpStatus.
     * @return ResponseEntity.
     */
    private ResponseEntity<Void> getVoidResponseEntity(UriComponentsBuilder builder, BlogPost blogPost, HttpStatus status) {

        UriComponents uriComponents = builder.path("/blogs/{id}").buildAndExpand(blogPost.getId());
        HttpHeaders header = new HttpHeaders();
        header.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(header, status);
    }

    /**
     * Edits blog post identified by id given in URL.
     *
     * @param id Id of blog post.
     * @param title Title of blog post.
     * @param content Content of blog post.
     * @param builder Used to build header of response.
     * @return Status CREATED if successful.
     */
    @PostMapping("/blogs/edit/{id:\\d}")
    public ResponseEntity<Void> editBlog(@PathVariable int id, @RequestParam String title, @RequestParam String content, UriComponentsBuilder builder) {
        Optional<BlogPost> optionalBlogPost = repository.findById(id);
        BlogPost blogPost = optionalBlogPost.get();
        blogPost.setTitle(title);
        blogPost.setContent(content);
        repository.save(blogPost);

        return getVoidResponseEntity(builder, blogPost, HttpStatus.CREATED);
    }

    /**
     * Adds comment to blog post identified by id given in URL.
     *
     * @param id Id of blog post.
     * @param content Content of comment.
     * @param builder Used to build header of response.
     * @return Status CREATED if successful.
     */
    @PostMapping("/blogs/addComment/{id:\\d}")
    public ResponseEntity<Void> addComment(@PathVariable int id, @RequestParam String content, UriComponentsBuilder builder) {
        Optional<BlogPost> optionalBlogPost = repository.findById(id);
        BlogPost blogPost = optionalBlogPost.get();
        blogPost.addComment(content);
        repository.save(blogPost);

        return getVoidResponseEntity(builder, blogPost, HttpStatus.CREATED);
    }

    /**
     * Delets comment of blog post identified by id given in URL.
     *
     * @param id Id of blog post.
     * @param commentId Id of comment.
     * @return Status OK if successful.
     */
    @DeleteMapping("/blogs/deleteComment/{id:\\d}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id, @RequestParam int commentId) {
        Optional<BlogPost> optionalBlogPost = repository.findById(id);
        BlogPost blogPost = optionalBlogPost.get();
        blogPost.deleteComment(commentId);
        repository.save(blogPost);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
