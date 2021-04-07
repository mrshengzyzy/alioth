package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;
import alioth.mrsheng.space.domain.blog.ArticlePage;
import alioth.mrsheng.space.domain.blog.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogBusinessImpl extends AbstractBlogSupport implements IBlogBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogBusinessImpl.class);

    @Resource
    private IBlogService blogService;

    @Override
    public ArticlePage pageByCatalogue(String catalogue, int pageNo, int pageSize) throws Exception {

        // 查询全部文章
        List<Article> articleList = blogService.queryArticles();

        // 统计标签数量
        Map<String, Integer> labelMap = countArticleLabels(articleList);

        // 友链
        List<Link> linkList = blogService.queryLinks();

        // 根据目录过滤
        if (!ALL_DIR.equals(catalogue)) {
            List<Article> list = new ArrayList<>();
            articleList.forEach(article -> {
                if (catalogue.equals(article.getCatalogue())) {
                    list.add(article);
                }
            });
            return toArticlePage(list, labelMap, linkList, pageNo, pageSize);
        }

        // 否则返回所有文章
        return toArticlePage(articleList, labelMap, linkList, pageNo, pageSize);
    }

    @Override
    public ArticlePage pageByLabel(String label, int pageNo, int pageSize) throws Exception {

        List<Article> articleList = blogService.queryArticles();
        Map<String, Integer> labelMap = countArticleLabels(articleList);
        List<Link> linkList = blogService.queryLinks();

        // 根据 label 过滤
        List<Article> list = new ArrayList<>();
        articleList.forEach(article -> {
            if (article.getLabels().contains(label)) {
                list.add(article);
            }
        });

        return toArticlePage(list, labelMap, linkList, pageNo, pageSize);
    }

    @Override
    public ArticlePage detail(String catalogue, String title) throws Exception {

        List<Article> articleList = blogService.queryArticles();
        Map<String, Integer> labelMap = countArticleLabels(articleList);
        List<Link> linkList = blogService.queryLinks();

        // 根据目录和标题过滤
        List<Article> detailList = articleList
                .stream()
                .filter(article -> catalogue.equals(article.getCatalogue()) && title.equals(article.getTitle()))
                .collect(Collectors.toList());
        if (detailList.size() != 1) {
            throw new Exception("cant query detail for [" + catalogue + "/" + title + "]");
        }

        ArticlePage page = new ArticlePage();
        page.setArticle(detailList.get(0));
        page.setLabelMap(labelMap);
        page.setLinkList(linkList);
        page.setPageType(ArticlePage.PageTypeDetail);
        return page;
    }

    private ArticlePage toArticlePage(List<Article> list,
                                      Map<String, Integer> labelMap,
                                      List<Link> linkList,
                                      int pageNo,
                                      int pageSize) {

        // 按照时间降序排序
        list.sort((o1, o2) -> -o1.getDateTime().compareTo(o2.getDateTime()));

        // 分页
        int count = list.size();
        int fromIndex = pageNo * pageSize;
        int toIndex = fromIndex + pageSize;
        if (toIndex > count) {
            toIndex = count;
        }
        List<Article> pageList = list.subList(fromIndex, toIndex);

        // 对象组装
        ArticlePage page = new ArticlePage();
        page.setPageNo(pageNo);
        page.setTotal(count);
        page.setArticleList(pageList);
        page.setLabelMap(labelMap);
        page.setLinkList(linkList);
        page.setPageType(ArticlePage.PageTypeList);
        return page;
    }
}
