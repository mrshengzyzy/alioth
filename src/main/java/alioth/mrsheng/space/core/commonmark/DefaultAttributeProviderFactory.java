package alioth.mrsheng.space.core.commonmark;

import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;

public class DefaultAttributeProviderFactory implements AttributeProviderFactory {

    @Override
    public AttributeProvider create(AttributeProviderContext context) {
        return (node, tagName, attributes) -> {

            if (node instanceof TableBlock) {
                attributes.put("class", "table table-condensed table-striped");
            }
        };
    }

    public static AttributeProviderFactory build() {
        return new DefaultAttributeProviderFactory();
    }
}
