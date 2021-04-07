package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.ArticlePage;

public interface IBlogBusiness {

    // 按目录查询
    ArticlePage pageByCatalogue(String catalogue, int pageNo, int pageSize) throws Exception;

    // 按标签查询
    ArticlePage pageByLabel(String label, int pageNo, int pageSize) throws Exception;

    // 文章详情
    ArticlePage detail(String catalogue, String title) throws Exception;
}
