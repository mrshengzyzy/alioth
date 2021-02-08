package alioth.mrsheng.space.core.commonmark.information;

import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;

public class InformationBlockRenderFactory implements HtmlNodeRendererFactory {

    @Override
    public NodeRenderer create(HtmlNodeRendererContext context) {
        return new InformationBlockRender(context);
    }

    public static InformationBlockRenderFactory build() {
        return new InformationBlockRenderFactory();
    }
}

