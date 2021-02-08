package alioth.mrsheng.space.service.blog;

public class Article {

    private String author;

    private String publishDateTime;

    private String content;

    public Article(String author, String publishDateTime, String content) {
        this.author = author;
        this.publishDateTime = publishDateTime;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(String publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
