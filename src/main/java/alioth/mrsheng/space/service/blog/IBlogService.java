package alioth.mrsheng.space.service.blog;

import java.util.List;

public interface IBlogService {

    List<Article> articleList();

    Article article(String title);

}
