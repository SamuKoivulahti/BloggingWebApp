package tamk.tiko.BloggingWebApp;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByName(String name);
}
