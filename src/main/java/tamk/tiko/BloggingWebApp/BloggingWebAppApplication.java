package tamk.tiko.BloggingWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the program.
 *
 * @author Joni Alanko <joni.alanko@tuni.fi>
 *         Samu Koivulahti <samu.koivulahti@tuni.fi>
 * @version 20190429
 * @since   1.8
 */
@SpringBootApplication
public class BloggingWebAppApplication implements CommandLineRunner {

    /**
     * Repository for users.
     */
	@Autowired
    UserRepository userRepository;

    /**
     * Repository for blog posts.
     */
	@Autowired
    MyRepository blogPostRepository;

    /**
     * Creates init data to all databases when program is started.
     * @param args Arguments of method. Not in use.
     * @throws Exception Exception if something goes wrong in creation of test data.
     */
    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("JoniAlanko", true, "Salasana1");
        User user2 = new User("SamuKoivulahti", false, "Salasana2");
        userRepository.save(user1);
        userRepository.save(user2);

        blogPostRepository.save(new BlogPost(user1.getName(), "Init", "Content"));
        blogPostRepository.save(new BlogPost(user2.getName(), "Init1241", "Conte2444nt"));
    }

    /**
     * Starts the program.
     * @param args Arguments of program. Not in use.
     */
    public static void main(String[] args) {
        SpringApplication.run(BloggingWebAppApplication.class, args);
    }
}
