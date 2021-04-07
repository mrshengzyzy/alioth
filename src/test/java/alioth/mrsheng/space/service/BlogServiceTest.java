package alioth.mrsheng.space.service;

import alioth.mrsheng.space.domain.blog.Article;
import alioth.mrsheng.space.domain.blog.Link;
import alioth.mrsheng.space.service.blog.IBlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTest {

    @Autowired
    private IBlogService blogService;

    @Test
    public void queryArticles() throws Exception {
        List<Article> articles = blogService.queryArticles();
        for (Article article : articles) {
            System.out.println(article.getTitle());
            System.out.println(article.getLabels());
        }
    }

    @Test
    public void queryLinks() throws Exception {
        List<Link> links = blogService.queryLinks();
        for (Link link : links) {
            System.out.println(link.getText() + ":" + link.getHref());
        }
    }
}
