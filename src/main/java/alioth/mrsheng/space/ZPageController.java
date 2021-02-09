package alioth.mrsheng.space;

import alioth.mrsheng.space.domain.blog.Article;
import alioth.mrsheng.space.service.blog.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ZPageController {

    @Resource
    private IBlogService blogService;

    @RequestMapping(value = {"/", "/index"})
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/post/all");
    }

    @RequestMapping(value = {"/post/{catalogue}"})
    public String post(Model model, @PathVariable String catalogue) throws IOException {
        Map<String, Object> attributes = new HashMap<>();
        List<Article> articleList = blogService.articleList(catalogue);
        attributes.put("articleList", articleList);
        model.addAllAttributes(attributes);
        return "index";
    }

    @RequestMapping(value = {"/blog/{catalogue}/{title}"})
    public String blog(Model model, @PathVariable String catalogue, @PathVariable String title) throws IOException {
        Article article = blogService.article(catalogue, title);
        model.addAttribute("article", article);
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
