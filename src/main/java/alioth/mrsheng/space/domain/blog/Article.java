package alioth.mrsheng.space.domain.blog;

import java.util.List;

public class Article {

    // 标题
    private String title;

    // 作者
    private String author;

    // 发布时间
    // YYYY-MM-DD HH:mm:ss 格式
    private String time;

    // 标签
    private List<String> labels;

    // 所处目录
    private String catalogue;

    // 完整内容
    private String html;

    // 简述
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
