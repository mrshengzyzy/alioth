package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.core.CommonMarkUtils;
import alioth.mrsheng.space.core.commonmark.information.InformationBlock;
import alioth.mrsheng.space.domain.blog.Article;
import cn.hutool.core.io.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Override
    public List<Article> articleList(String catalogue) throws IOException {

        List<Article> articleList = new ArrayList<>();

        String locationPattern = "all".equals(catalogue) ? "blogs/**" : "blogs/" + catalogue + "/**";
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(locationPattern);
        for (Resource resource : resources) {
            Article article = convertFromResource(resource);
            if (article != null) {
                articleList.add(article);
            }
        }
        return articleList;
    }

    @Override
    public Article article(String catalogue, String title) throws IOException {
        String locationPattern = "blogs/" + catalogue + "/" + title + ".md";
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource(locationPattern);
        return convertFromResource(resource);
    }

    private Article convertFromResource(Resource resource) throws IOException {

        // 获取待转换的文件
        File file = resource.getFile();

        // should neve happened
        if (!file.isFile()) {
            return null;
        }

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
}
