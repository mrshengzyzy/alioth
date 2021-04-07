package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;
import alioth.mrsheng.space.domain.blog.Link;

import java.util.List;

public interface IBlogService {

    /**
     * 获取文章列表
     */
    List<Article> queryArticles() throws Exception;

    /**
     * 获取友链列表
     */
    List<Link> queryLinks() throws Exception;
}
