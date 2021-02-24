package alioth.mrsheng.space;

import alioth.mrsheng.space.domain.blog.ArticlePage;
import alioth.mrsheng.space.service.blog.IBlogBusiness;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        ArticlePage articlePage = blogBusiness.pages(catalogue);
        model.addAttribute("articles",articlePage.getArticles());
        model.addAttribute("labels",articlePage.getLabels());
        return "index";
    }

    @RequestMapping(value = {"/blog/{catalogue}/{title}"})
    public String blog(Model model, @PathVariable String catalogue, @PathVariable String title) throws Exception {
        ArticlePage articlePage = blogBusiness.detail(catalogue, title);
        model.addAttribute(articlePage);
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
