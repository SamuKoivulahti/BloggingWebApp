package tamk.tiko.BloggingWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggingWebAppApplication implements CommandLineRunner {

	@Autowired
    UserRepository userRepository;

	@Autowired
    MyRepository blogPostRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("JoniAlanko", true);
        User user2 = new User("SamuKoivulahti", false);
        userRepository.save(user1);
        userRepository.save(user2);

    }

    public static void main(String[] args) {
        SpringApplication.run(BloggingWebAppApplication.class, args);
    }
}
