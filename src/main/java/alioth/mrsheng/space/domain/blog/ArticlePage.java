package alioth.mrsheng.space.domain.blog;

import java.util.List;
import java.util.Map;

public class ArticlePage {

    // 页面类型
    // 指示该按照列表还是详情展示页面
    private String pageType;
    public static final String PageTypeList = "list";
    public static final String PageTypeDetail = "detail";

    // 文章详情
    private Article article;

    // 文章列表
    private List<Article> articleList;

    // 友链
    private List<Link> linkList;

    // 标签
    private Map<String, Integer> labelMap;

    // 分页参数
    private int total;
    private int pageNo;

    // 默认每页数据条数
    // 这个值应该由前端指定,但是不知道如何在 thymeleaf 引用定义的 js 变量
    private final int defaultPageSize = 7;

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Map<String, Integer> getLabelMap() {
        return labelMap;
    }

    public void setLabelMap(Map<String, Integer> labelMap) {
        this.labelMap = labelMap;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }
}
