package tamk.tiko.BloggingWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<Void> addUser(@RequestParam String name, @RequestParam boolean admin, @RequestParam String pass, UriComponentsBuilder builder) {
        User user = new User(name, admin, pass);
        userRepository.save(user);
        UriComponents uriComponents = builder.path("users/{id}").buildAndExpand(user.getUserId());

        HttpHeaders header = new HttpHeaders();
        header.setLocation(uriComponents.toUri());
        return new ResponseEntity<Void>(header, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id:\\d}")
    public ResponseEntity<Void> removeUser(@PathVariable int id, UriComponentsBuilder builder) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(id,"Cannot find user with id  " +  id);
        }
    }

    @GetMapping("/users/{id:\\d}")
    public User getUser(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "Cannot find user with id  " +  id));
    }

    @GetMapping("/users/{name}")
    public User getUserByName(@PathVariable String name) {
        return userRepository.findByNameEquals(name);
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/blogs/like/{id:\\d}")
    public void addLike(@PathVariable int id, @RequestParam String user, UriComponentsBuilder builder) {
        User userLike = userRepository.findByNameEquals(user);
        userLike.addLikes(id);
        userRepository.save(userLike);
    }

    @DeleteMapping("/blogs/like/del/{id:\\d}")
    public void deleteLike(@PathVariable Integer id, @RequestParam String user, UriComponentsBuilder builder) {
        User userLike = userRepository.findByNameEquals(user);
        userLike.deleteLikes(id);
        userRepository.save(userLike);
    }

}