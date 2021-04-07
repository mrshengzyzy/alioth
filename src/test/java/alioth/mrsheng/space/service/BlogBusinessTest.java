package alioth.mrsheng.space.service;

import alioth.mrsheng.space.domain.blog.ArticlePage;
import alioth.mrsheng.space.service.blog.IBlogBusiness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogBusinessTest {

    @Autowired
    private IBlogBusiness blogBusiness;

    @Test
    public void articlePage() throws Exception {
        ArticlePage page = blogBusiness.pageByCatalogue("all", 0, 4);
        System.out.println(page.getPageNo());
        System.out.println(page.getTotal());
        page.getArticleList().forEach(
                article -> {
                    System.out.println(article.getCatalogue());
                    System.out.println(article.getTitle());
                    System.out.println(article.getDateTime());
                    System.out.println("=============");
                }
        );
    }

    @Test
    public void articleDetail() throws Exception {
        ArticlePage page = blogBusiness.detail("raspberry", "raspberry1");
        System.out.println(page.getArticle());
    }
}
