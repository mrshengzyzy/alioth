package alioth.mrsheng.space.core.commonmark.information;

import cn.hutool.core.util.StrUtil;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.HtmlBlock;

import java.util.Arrays;

public class InformationBlockVisitor extends AbstractVisitor {

    private InformationBlock informationBlock;

    @Override
    public void visit(HtmlBlock block) {

        // 每次调用都创建新对象
        informationBlock = new InformationBlock();

        // 解析信息节点
        if (InformationBlock.isTrue(block)) {

            String content = block.getLiteral();
            String[] lines = content.split("\n");
            for (String line : lines) {

                // 不处理起止行
                int p = line.indexOf(":");
                if (p == -1) {
                    continue;
                }
                String key = line.substring(0, p);
                String value = StrUtil.trim(line.substring(p + 1));

                switch (key) {
                    case InformationBlock.AUTHOR:
                        informationBlock.setAuthor(value);
                        continue;
                    case InformationBlock.TIME:
                        informationBlock.setTime(value);
                        continue;
                    case InformationBlock.TITLE:
                        informationBlock.setTitle(value);
                        continue;
                    case InformationBlock.CATALOGUE:
                        informationBlock.setCatalogue(value);
                        continue;
                    case InformationBlock.LABELS:
                        informationBlock.setLabels(Arrays.asList(value.split(",")));
                        continue;
                    default:
                        System.out.println("commonmark unrecognized: " + line);
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
