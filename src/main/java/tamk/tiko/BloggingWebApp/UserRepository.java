package tamk.tiko.BloggingWebApp;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface for user management.
 *
 * @author Joni Alanko <joni.alanko@tuni.fi>
 *         Samu Koivulahti <samu.koivulahti@tuni.fi>
 * @version 20190429
 * @since   1.8
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Finds user with given name.
     * @param name Name of user to find.
     * @return User.
     */
    User findByNameEquals(String name);
}
