package com.commonmark;

import alioth.mrsheng.space.core.CommonMarkUtils;
import alioth.mrsheng.space.core.commonmark.information.InformationBlock;
import cn.hutool.core.io.FileUtil;
import org.junit.Test;

public class Markdown2HtmlTest {

    @Test
    public void render() {
        String content = FileUtil.readUtf8String("D:\\test.md");
        InformationBlock block = CommonMarkUtils.markdown2Html(content);
        System.out.println(block);
        System.out.println(block.getHtml());
    }
}
