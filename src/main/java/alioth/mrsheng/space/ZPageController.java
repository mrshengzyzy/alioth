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

    /**
     * 根目录请求跳转
     */
    @RequestMapping(value = {"/", "/index"})
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/blog/catalogue/all/0/7");
    }

    /**
     * 根据目录查询文章列表
     *
     * @param catalogue 目录名称
     * @param pageNo    页码(从0开始)
     * @param pageSize  页面大小
     */
    @RequestMapping(value = {"/blog/catalogue/{catalogue}/{pageNo}/{pageSize}"})
    public String pageByCatalogue(Model model,
                                  @PathVariable String catalogue,
                                  @PathVariable Integer pageNo,
                                  @PathVariable Integer pageSize) throws Exception {
        ArticlePage articlePage = blogBusiness.pageByCatalogue(catalogue, pageNo, pageSize);
        model.addAttribute("context", articlePage);
        return "index";
    }

    /**
     * 根据标签查询文章列表
     */
    @RequestMapping(value = {"/blog/label/{label}/{pageNo}/{pageSize}"})
    public String pageByLabel(Model model,
                              @PathVariable String label,
                              @PathVariable Integer pageNo,
                              @PathVariable Integer pageSize) throws Exception {
        ArticlePage articlePage = blogBusiness.pageByLabel(label, pageNo, pageSize);
        model.addAttribute("context", articlePage);
        return "index";
    }

    /**
     * 根据目录与标题查询文章详情
     *
     * @param catalogue 目录
     * @param title     标题
     */
    @RequestMapping(value = {"/blog/detail/{catalogue}/{title}"})
    public String detail(Model model, @PathVariable String catalogue, @PathVariable String title) throws Exception {
        ArticlePage articlePage = blogBusiness.detail(catalogue, title);
        model.addAttribute("context", articlePage);
        return "index";
    }
}
