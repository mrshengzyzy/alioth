package alioth.mrsheng.space.core;

import alioth.mrsheng.space.core.commonmark.DefaultAttributeProviderFactory;
import alioth.mrsheng.space.core.commonmark.information.InformationBlock;
import alioth.mrsheng.space.core.commonmark.information.InformationBlockRenderFactory;
import alioth.mrsheng.space.core.commonmark.information.InformationBlockVisitor;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.ArrayList;
import java.util.List;

public class CommonMarkUtils {

    private static List<Extension> defaultExtensions() {
        List<Extension> extensions = new ArrayList<>();

        // 表格扩展
        Extension tableExtension = TablesExtension.create();
        extensions.add(tableExtension);
        return extensions;
    }

    private static Parser defaultParser() {
        List<Extension> extensions = defaultExtensions();
        return Parser.builder().extensions(extensions).build();
    }

    private static HtmlRenderer defaultRender() {
        List<Extension> extensions = defaultExtensions();
        return HtmlRenderer.builder()
                .extensions(extensions)
                .attributeProviderFactory(DefaultAttributeProviderFactory.build())
                .nodeRendererFactory(InformationBlockRenderFactory.build())
                .build();
    }

    /**
     * 将 MD 文件转换为 InformationBlock
     * 转换失败将报错
     */
    public static InformationBlock markdown2InformationBlock(String content) {

        // 为节点添加自定义的 Visitor
        InformationBlockVisitor visitor = InformationBlockVisitor.build();
        Node document = defaultParser().parse(content);
        document.accept(visitor);

        // 返回渲染对象
        String html = defaultRender().render(document);

        // 解析为节点对象
        InformationBlock block = visitor.getInformationBlock();
        block.setHtml(html);

        return block;
    }

    /**
     * 将 MD 转换为 html
     */
    public static String markdown2Html(String content) {
        Node document = defaultParser().parse(content);
        return defaultRender().render(document);
    }
}
