package org.commonmark;

import alioth.mrsheng.space.core.CommonMarkUtils;
import cn.hutool.core.io.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;

public class LinkTest {

    /**
     * 解析友链页面
     */
    @Test
    public void link() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("blogs/link.md");
        File file = resource.getFile();
        String html = CommonMarkUtils.markdown2Html(FileUtil.readUtf8String(file));
        Document doc = Jsoup.parseBodyFragment(html);
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();

            System.out.println(linkText);
            System.out.println(linkHref);
            System.out.println("=====================");
        }
    }
}
