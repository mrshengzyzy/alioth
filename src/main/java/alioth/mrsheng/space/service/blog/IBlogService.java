package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;

import java.io.IOException;
import java.util.List;

public interface IBlogService {

    List<Article> articleList(String catalogue) throws IOException;

    Article article(String catalogue, String title) throws IOException;
}
