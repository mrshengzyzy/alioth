package alioth.mrsheng.space;

import alioth.mrsheng.space.domain.blog.Article;
import alioth.mrsheng.space.service.blog.IBlogBusiness;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ZPageController {

    @Resource
    private IBlogBusiness blogBusiness;

    @RequestMapping(value = {"/", "/index"})
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/post/all");
    }

    @RequestMapping(value = {"/post/{catalogue}"})
    public String post(Model model, @PathVariable String catalogue) throws Exception {
        List<Article> articles = blogBusiness.pages(catalogue);
        Map<String, Integer> labels = blogBusiness.labels();
        model.addAttribute("articles", articles);
        model.addAttribute("labels", labels);
        return "index";
    }

    @RequestMapping(value = {"/post/label/{label}"})
    public String labelPost(Model model, @PathVariable String label) throws Exception {
        List<Article> articles = blogBusiness.labelPages(label);
        Map<String, Integer> labels = blogBusiness.labels();
        model.addAttribute("articles", articles);
        model.addAttribute("labels", labels);
        return "index";
    }

    @RequestMapping(value = {"/blog/{catalogue}/{title}"})
    public String blog(Model model, @PathVariable String catalogue, @PathVariable String title) throws Exception {
        Article article = blogBusiness.detail(catalogue, title);
        Map<String, Integer> labels = blogBusiness.labels();
        model.addAttribute("article", article);
        model.addAttribute("labels", labels);
        return "blog";
    }

    @RequestMapping(value = {"/love"})
    public String love() {
        return "love";
    }

    @RequestMapping(value = {"/about"})
    public String about() {
        return "about";
    }
}
