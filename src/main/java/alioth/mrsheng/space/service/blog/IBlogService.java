package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IBlogService {

    List<Article> articleList(String catalogue) throws IOException;

    Article article(String catalogue, String title) throws IOException;

    Map<String, Integer> countLabels(String catalogue) throws IOException;
}
