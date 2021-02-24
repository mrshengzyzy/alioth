package alioth.mrsheng.space.service;

import alioth.mrsheng.space.domain.blog.Article;
import alioth.mrsheng.space.service.blog.IBlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTest {

    @Autowired
    private IBlogService blogService;

    @Test
    public void listArticles() throws Exception {
        List<Article> articles = blogService.articleList("all");
        for (Article article : articles) {
            System.out.println(article.getTitle());
            System.out.println(article.getLabels());
        }
    }

    @Test
    public void printArticle() throws Exception {
        Article article = blogService.articleDetail("golang");
        System.out.println(article);
    }
}
