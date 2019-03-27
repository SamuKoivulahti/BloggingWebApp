package tamk.tiko.BloggingWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Optional;


@RestController
public class BasicController {
    
    @Autowired
    MyRepository repository;

    @GetMapping("/blogs/{id:\\d}")
    public BlogPost getBlogPost(@PathVariable int id) {

        return repository.findById(id).orElseThrow(() -> new NotFoundException(id, "BlogPost " + id + " not found"));
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }

    @GetMapping("/blogs")
    public Iterable<BlogPost> getAllBlogPosts() {
        return repository.findAll();
    }

    @DeleteMapping("/blogs/{id:\\d}")
    public ResponseEntity<Void> removeBlogPost(@PathVariable int id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/blogs")
    public ResponseEntity<Void> addBlogPost(@RequestParam String user, @RequestParam String title, @RequestParam String content, UriComponentsBuilder builder) {
        BlogPost blogPost = new BlogPost(user, title, content);
        repository.save(blogPost);

        return getVoidResponseEntity(builder, blogPost, HttpStatus.CREATED);
    }

    private ResponseEntity<Void> getVoidResponseEntity(UriComponentsBuilder builder, BlogPost blogPost, HttpStatus status) {

        UriComponents uriComponents = builder.path("/blogs/{id}").buildAndExpand(blogPost.getId());
        HttpHeaders header = new HttpHeaders();
        header.setLocation(URI.create("/"));

        return new ResponseEntity<Void>(header, status);
    }

    @PostMapping("/blogs/edit/{id:\\d}")
    public ResponseEntity<Void> editBlog(@PathVariable int id, @RequestParam String title, @RequestParam int authorId, @RequestParam String content, UriComponentsBuilder builder) {
        Optional<BlogPost> optionalBlogPost = repository.findById(id);
        BlogPost blogPost = optionalBlogPost.get();
        blogPost.setTitle(title);
        blogPost.setContent(content);
        repository.save(blogPost);

        return getVoidResponseEntity(builder, blogPost, HttpStatus.CREATED);
    }
}
