package alioth.mrsheng.space.domain.blog;

import java.util.List;
import java.util.Map;

public class ArticlePage {

    private List<Article> articles;

    private Article article;

    private Map<String, Integer> labels;

    public ArticlePage() {
    }

    public ArticlePage(List<Article> articles, Article article, Map<String, Integer> labels) {
        this.articles = articles;
        this.article = article;
        this.labels = labels;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Map<String, Integer> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, Integer> labels) {
        this.labels = labels;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
