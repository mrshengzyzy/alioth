package alioth.mrsheng.space;

import alioth.mrsheng.space.service.blog.Article;
import alioth.mrsheng.space.service.blog.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ZPageController {

    @Resource
    private IBlogService pageService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("page", "post");
        model.addAttribute("fragment", "list");

        List<Article> articleList = pageService.articleList();
        model.addAttribute("articleList", articleList);

        return "index";
    }

    @RequestMapping(value = {"/golang"})
    public String golang(Model model) {
        model.addAttribute("page", "post");
        model.addAttribute("fragment", "post");
        return "index";
    }

    @RequestMapping(value = {"/raspberry"})
    public String raspberryPi(Model model) {
        model.addAttribute("page", "post");
        model.addAttribute("fragment", "post");
        return "index";
    }

    @RequestMapping(value = {"/compiler"})
    public String compiler(Model model) {
        model.addAttribute("page", "post");
        model.addAttribute("fragment", "post");
        return "index";
    }

    @RequestMapping(value = {"/about"})
    public String about(Model model) {
        model.addAttribute("page", "post");
        model.addAttribute("fragment", "post");
        return "index";
    }

    @RequestMapping(value = {"/love"})
    public String love(Model model) {
        model.addAttribute("page", "love");
        model.addAttribute("fragment", "love");
        return "index";
    }
}
