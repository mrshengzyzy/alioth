package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;

import java.util.List;
import java.util.Map;

public interface IBlogBusiness {

    List<Article> pages(String catalogue) throws Exception;

    List<Article> labelPages(String label) throws Exception;

    Article detail(String catalogue, String title) throws Exception;

    Map<String, Integer> labels() throws Exception;
}
