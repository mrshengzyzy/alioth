package alioth.mrsheng.space.core.commonmark.information;

import org.commonmark.node.HtmlBlock;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.CoreHtmlNodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;

import java.util.Collections;
import java.util.Set;

public class InformationBlockRender extends CoreHtmlNodeRenderer implements NodeRenderer {

    protected InformationBlockRender(HtmlNodeRendererContext context) {
        super(context);
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(HtmlBlock.class);
    }

    @Override
    public void render(Node node) {

        HtmlBlock htmlBlock = (HtmlBlock) node;

        // 文章信息块不进行渲染
        if (InformationBlock.isTrue(htmlBlock)) {
            return;
        }

        // 其他块仍旧调用原始逻辑进行渲染
        node.accept(this);
    }
}
