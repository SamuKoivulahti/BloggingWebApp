package tamk.tiko.BloggingWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggingWebAppApplication implements CommandLineRunner {

	@Autowired
    HtmlHelper htmlHelper;

    @Override
    public void run(String... args) throws Exception {
        //htmlHelper.createHtmlPage("Blog", "Mitä ikinä sinne menee sinne bodin sisään.");
    }

    public static void main(String[] args) {
        SpringApplication.run(BloggingWebAppApplication.class, args);
    }
}
