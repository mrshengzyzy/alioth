package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.ArticlePage;

public interface IBlogBusiness {

    ArticlePage pages(String catalogue) throws Exception;

    ArticlePage detail(String catalogue, String title) throws Exception;
}
