package alioth.mrsheng.space.core.commonmark.information;

import cn.hutool.core.util.StrUtil;
import org.commonmark.node.HtmlBlock;

public class InformationBlockConst {

    // 页面标签
    private static final String START_LABEL = "<?";
    private static final String END_LABEL = "?>";
    public static final String TITLE_LABEL = "title";
    public static final String AUTHOR_LABEL = "author";
    public static final String TIME_LABEL = "time";
    public static final String LABELS_LABEL = "labels";
    public static final String BRIEF_LABEL = "brief";

    /**
     * 判断是否是 Information 节点
     */
    public static boolean isInformationBlock(HtmlBlock block) {
        String comment = block.getLiteral();
        return StrUtil.startWith(comment, START_LABEL) && StrUtil.endWith(comment, END_LABEL);
    }
}
