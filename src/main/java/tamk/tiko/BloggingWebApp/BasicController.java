package tamk.tiko.BloggingWebApp;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

    @RequestMapping(value = "/webapp", method = RequestMethod.POST)
    @ResponseBody
    public String greeting(@RequestParam ("text") String text, Model model) {
        model.addAttribute("body", text);
        return "index";
    }
}
