package tamk.tiko.BloggingWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller class for user data.
 *
 * @author Joni Alanko <joni.alanko@tuni.fi>
 *         Samu Koivulahti <samu.koivulahti@tuni.fi>
 * @version 20190429
 * @since   1.8
 */
@RestController
public class UserController {

    /**
     * Repository of users.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Adds user to database.
     *
     * @param name Name of user.
     * @param admin Admin status of user.
     * @param pass Password of user.
     * @param builder Used to build header of response.
     * @return Status CREATED if successful.
     */
    @PostMapping("/users")
    public ResponseEntity<Void> addUser(@RequestParam String name, @RequestParam boolean admin, @RequestParam String pass, UriComponentsBuilder builder) {
        User user = new User(name, admin, pass);
        userRepository.save(user);
        UriComponents uriComponents = builder.path("users/{id}").buildAndExpand(user.getUserId());

        HttpHeaders header = new HttpHeaders();
        header.setLocation(uriComponents.toUri());
        return new ResponseEntity<Void>(header, HttpStatus.CREATED);
    }

    /**
     * Deletes user with id given in URL.
     *
     * @param id Id of user.
     * @param builder Used to build header of response.
     * @return Status NO_CONTENT if successful. Throws NotFoundException if user is not found.
     */
    @DeleteMapping("/users/{id:\\d}")
    public ResponseEntity<Void> removeUser(@PathVariable int id, UriComponentsBuilder builder) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(id,"Cannot find user with id  " +  id);
        }
    }

    /**
     * Returns user with given id.
     *
     * @param id Id of user.
     * @return User with given id.
     */
    @GetMapping("/users/{id:\\d}")
    public User getUser(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "Cannot find user with id  " +  id));
    }

    /**
     * Returns user with given name.
     *
     * @param name Name of user.
     * @return User with given name.
     */
    @GetMapping("/users/{name}")
    public User getUserByName(@PathVariable String name) {
        return userRepository.findByNameEquals(name);
    }

    /**
     * Returns all users from database.
     *
     * @return All users.
     */
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Adds a like.
     *
     * @param id Id of blog post.
     * @param user User who liked.
     * @param builder Used to build header of response.
     */
    @PostMapping("/blogs/like/{id:\\d}")
    public void addLike(@PathVariable int id, @RequestParam String user, UriComponentsBuilder builder) {
        User userLike = userRepository.findByNameEquals(user);
        userLike.addLikes(id);
        userRepository.save(userLike);
    }

    /**
     * Deletes a like.
     *
     * @param id Id of blog post.
     * @param user User who liked.
     * @param builder Used to build header of response.
     */
    @DeleteMapping("/blogs/like/del/{id:\\d}")
    public void deleteLike(@PathVariable Integer id, @RequestParam String user, UriComponentsBuilder builder) {
        User userLike = userRepository.findByNameEquals(user);
        userLike.deleteLikes(id);
        userRepository.save(userLike);
    }

}