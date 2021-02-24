package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.core.CommonMarkUtils;
import alioth.mrsheng.space.core.commonmark.information.InformationBlock;
import alioth.mrsheng.space.domain.blog.Article;
import cn.hutool.core.io.FileUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBlogServiceImpl {

    public static final String ALL_CATALOGUE = "all";

    public static final String BLOG_DIR = "blogs";

    protected List<File> loadFiles(String locationPattern) throws IOException {
        List<File> fileList = new ArrayList<>();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(locationPattern);
        for (Resource resource : resources) {
            if (resource == null) {
                continue;
            }
            File file = resource.getFile();
            if (file.isFile()) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    protected File loadFile(String locationPattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource(locationPattern);
        return resource.getFile();
    }

    protected Article fileToArticle(File file) throws IOException {

        // 读取文件内容并转换为 html
        String content = FileUtil.readUtf8String(file);
        InformationBlock block = CommonMarkUtils.markdown2Html(content);

        // 转换为 Article 对象
        Article article = new Article();
        article.setTitle(block.getTitle());
        article.setAuthor(block.getAuthor());
        article.setTime(block.getTime());
        article.setLabels(block.getLabels());
        article.setCatalogue(block.getCatalogue());
        article.setHtml(block.getHtml());
        article.setDescription("如何获取简介内容?");
        return article;
    }

    protected String catalogueToLocationPattern(String catalogue){
        return ALL_CATALOGUE.equals(catalogue) ? BLOG_DIR + "/**" : BLOG_DIR + "/" + catalogue + "/**";
    }
}
