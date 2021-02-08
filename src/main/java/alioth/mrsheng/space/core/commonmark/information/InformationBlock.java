package alioth.mrsheng.space.core.commonmark.information;

import cn.hutool.core.util.StrUtil;
import org.commonmark.node.HtmlBlock;

import java.util.List;

// 信息节点
public class InformationBlock extends HtmlBlock {

    private static final String StartLabel = "<?";
    private static final String EndLabel = "?>";

    public static final String AUTHOR = "author";
    public static final String TIME = "time";
    public static final String TITLE = "title";
    public static final String LABELS = "labels";

    public static boolean isTrue(HtmlBlock block) {
        String comment = block.getLiteral();
        return StrUtil.startWith(comment, StartLabel) && StrUtil.endWith(comment, EndLabel);
    }

    private String author;

    private String time;

    private String title;

    private List<String> labels;

    private String html;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "InformationBlock{" + "author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", labels=" + labels +
                ", html=<omit>}";
    }
}
