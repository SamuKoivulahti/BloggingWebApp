package tamk.tiko.BloggingWebApp;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class BasicController {
    private List<BlogPost> blogs = new ArrayList<>();

    @Autowired
    HtmlHelper htmlHelper;

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("blogs", blogs);
        return "form";
    }

    @PostMapping("/form")
    public String postForm(@RequestParam ("content") String content, @RequestParam ("title") String title, Model model) {
        BlogPost bp = new BlogPost(1, "Default", title, content);
        //String[] blogPostContent = {bp.getTitle(), bp.getName(),bp.getContent()};

        //blogs.add(htmlHelper.createHtmlTable(blogPostContent));
        blogs.add(bp);
        Collections.reverse(blogs);

        model.addAttribute("blogs", blogs);
        return "form";
    }
}
