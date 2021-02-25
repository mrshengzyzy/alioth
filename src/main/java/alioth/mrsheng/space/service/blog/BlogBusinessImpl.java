package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BlogBusinessImpl extends AbstractBlogSupport implements IBlogBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogBusinessImpl.class);

    @Resource
    private IBlogService blogService;

    @Override
    public List<Article> pages(String catalogue) throws Exception {
        String locationPattern = catalogueToLocationPattern(catalogue);
        return blogService.articleList(locationPattern);
    }

    @Override
    public List<Article> labelPages(String label) throws Exception {
        List<Article> response = new ArrayList<>();
        String locationPattern = catalogueToLocationPattern(ALL_CATALOGUE);
        List<Article> articleList = blogService.articleList(locationPattern);
        articleList.forEach(article -> {
            if (article.getLabels().contains(label)) {
                response.add(article);
            }
        });
        return response;
    }

    @Override
    public Article detail(String catalogue, String title) throws Exception {
        String locationPattern = BLOG_DIR + "/" + catalogue + "/" + title + ".md";
        return blogService.articleDetail(locationPattern);
    }

    @Override
    public Map<String, Integer> labels() throws Exception {
        String locationPattern = catalogueToLocationPattern(ALL_CATALOGUE);
        List<Article> articleList = blogService.articleList(locationPattern);
        return countArticleLabels(articleList);
    }
}
