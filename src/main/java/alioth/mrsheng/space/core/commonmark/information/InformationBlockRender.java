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

        // 当前 Node 节点是文章节点时不进行渲染(转换为html)
        if (InformationBlockConst.isInformationBlock(htmlBlock)) {
            return;
        }

        node.accept(this);
    }
}
