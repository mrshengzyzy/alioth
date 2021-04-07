package alioth.mrsheng.space.domain.blog;

import java.time.LocalDateTime;
import java.util.List;

public class Article {

    // 标题
    private String title;

    // 作者
    private String author;

    // 发布时间
    // yyyy-MM-dd HH:mm:ss
    private LocalDateTime dateTime;

    // 页面展示使用
    // 当前实际只展示了日期
    private String dateTimeString;

    // 标签
    private List<String> labels;

    // 所处目录
    private String catalogue;

    // 完整内容
    private String html;

    // 简述
    private String brief;

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDateTimeString() {
        return dateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }

    @Override
    public String toString() {
        return "Article{" + "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", dateTime=" + dateTime +
                ", labels=" + labels +
                ", catalogue='" + catalogue + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
