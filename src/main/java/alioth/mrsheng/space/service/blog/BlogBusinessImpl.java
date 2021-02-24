package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;
import alioth.mrsheng.space.domain.blog.ArticlePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BlogBusinessImpl extends AbstractBlogSupport implements IBlogBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogBusinessImpl.class);

    @Resource
    private IBlogService blogService;

    @Override
    public ArticlePage pages(String catalogue) throws Exception {

        // 文章列表
        String locationPattern = catalogueToLocationPattern(catalogue);
        List<Article> articleList = blogService.articleList(locationPattern);

        // 标签统计
        Map<String, Integer> map = countAllLabels();
        return new ArticlePage(articleList, null, map);
    }

    @Override
    public ArticlePage detail(String catalogue, String title) throws Exception {

        // 请求文章详情
        String locationPattern = BLOG_DIR + "/" + catalogue + "/" + title + ".md";
        Article article = blogService.articleDetail(locationPattern);

        // 标签统计
        Map<String, Integer> map = countAllLabels();
        return new ArticlePage(null, article, map);
    }

    private Map<String, Integer> countAllLabels() throws Exception {
        String locationPattern = catalogueToLocationPattern(ALL_CATALOGUE);
        List<Article> articleList = blogService.articleList(locationPattern);
        return countArticleLabels(articleList);
    }
}
