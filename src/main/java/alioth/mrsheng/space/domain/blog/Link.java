package alioth.mrsheng.space.domain.blog;

/**
 * 友链
 * 友链信息写在 blogs 目录下的 links.md 文件中
 */
public class Link {

    public static final String LinkFile = "link.md";

    private String text;

    private String href;

    public Link() {

    }

    public Link(String text, String href) {
        this.text = text;
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Link{" + "text='" + text + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
