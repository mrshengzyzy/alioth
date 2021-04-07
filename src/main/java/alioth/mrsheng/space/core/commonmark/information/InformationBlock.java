package alioth.mrsheng.space.core.commonmark.information;

import org.commonmark.node.HtmlBlock;

import java.util.List;

// 信息节点
public class InformationBlock extends HtmlBlock {

    // 标题,必须唯一
    private String title;

    // 作者
    private String author;

    // 发布时间
    private String time;

    // 文章标签
    private List<String> labels;

    // 简介
    private String brief;

    // markdown 转换为 html 内容
    private String html;

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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "InformationBlock{" + "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", labels=" + labels +
                ", brief='" + brief + '\'' +
                '}';
    }
}
