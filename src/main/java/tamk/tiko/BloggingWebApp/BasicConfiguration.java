package tamk.tiko.BloggingWebApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BasicConfiguration {

    @Bean
    @Scope("prototype")
    public HtmlHelper getHtmlHelper() {
        return new HtmlCreator();
    }
}
