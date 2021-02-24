package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IBlogService {

    List<Article> articleList(String dir) throws IOException;

    Article articleDetail(String location) throws IOException;
}
