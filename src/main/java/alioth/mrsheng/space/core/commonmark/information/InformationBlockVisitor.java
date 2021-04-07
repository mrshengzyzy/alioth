package alioth.mrsheng.space.core.commonmark.information;

import cn.hutool.core.util.StrUtil;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.HtmlBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class InformationBlockVisitor extends AbstractVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InformationBlockVisitor.class);

    private InformationBlock informationBlock;

    @Override
    public void visit(HtmlBlock block) {

        // 每次调用都创建新对象
        informationBlock = new InformationBlock();

        // 解析信息节点
        if (InformationBlockConst.isInformationBlock(block)) {

            // 获取原始文本内容
            String content = block.getLiteral();

            /*
             * 按行处理
             * <?
             * title: 标题唯一
             * author: 作者
             * labels: 标签,使用英文逗号,分隔
             * time: YYYY-MM-DD HH:mm:ss 格式
             * brief: 文章简述(就是列表看到的部分)
             * ?>
             */
            String[] lines = content.split("\n");
            for (String line : lines) {

                // 不处理起止行
                int p = line.indexOf(":");
                if (p == -1) {
                    continue;
                }

                // 以冒号为分隔符切割
                String key = line.substring(0, p);
                String value = StrUtil.trim(line.substring(p + 1));

                switch (key) {
                    case InformationBlockConst.AUTHOR_LABEL:
                        informationBlock.setAuthor(value);
                        continue;
                    case InformationBlockConst.TIME_LABEL:
                        informationBlock.setTime(value);
                        continue;
                    case InformationBlockConst.TITLE_LABEL:
                        informationBlock.setTitle(value);
                        continue;
                    case InformationBlockConst.BRIEF_LABEL:
                        informationBlock.setBrief(value);
                        continue;
                    case InformationBlockConst.LABELS_LABEL:
                        informationBlock.setLabels(Arrays.asList(value.split(",")));
                        continue;
                    default:
                        LOGGER.error("commonmark unrecognized line[" + line + "]");
                }
            }
        }
    }

    public InformationBlock getInformationBlock() {
        return informationBlock;
    }

    public static InformationBlockVisitor build() {
        return new InformationBlockVisitor();
    }
}
