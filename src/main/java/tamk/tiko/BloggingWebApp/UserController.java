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
    public ResponseEntity<Void> addUser(User user, UriComponentsBuilder builder) {
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

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}