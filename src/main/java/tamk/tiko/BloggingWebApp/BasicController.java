package tamk.tiko.BloggingWebApp;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BasicController {
    private List<BlogPost> blogs = new ArrayList<>();
    private int id = 0;

    @Autowired
    HtmlHelper htmlHelper;

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("blogs", blogs);
        return "form";
    }

    @PostMapping("/form")
    public String postForm(@RequestParam ("content") String content, @RequestParam ("title") String title, Model model) {
        BlogPost bp = new BlogPost(id, "UserName", title, content);
        id++;
        blogs.add(0, bp);

        System.out.println("Something Happened with postForm and ID is: " + bp.toString());

        model.addAttribute("blogs", blogs);
        return "form";
    }

    @PostMapping("/form/{id}")
    public String postDelete(@PathVariable int id, Model model) {

        System.out.println("Something Happened with postDelete and ID is: " + id);

        //blogs.add(htmlHelper.createHtmlTable(blogPostContent));
        for (int i = 0; i < blogs.size(); i++) {
            if (id == blogs.get(i).getId()) {
                blogs.remove(i);
                break;
            }
        }

        model.addAttribute("blogs", blogs);
        return "redirect:/";
    }
}
