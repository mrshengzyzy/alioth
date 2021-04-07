package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.core.CommonMarkUtils;
import alioth.mrsheng.space.core.commonmark.information.InformationBlock;
import alioth.mrsheng.space.domain.blog.Article;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBlogSupport {

    public static final String BLOG_DIR = "blogs";
    public static final String ALL_DIR = "all";

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

    protected Article fileToArticle(File file) {

        // 提取目录
        String path = file.getPath();
        List<String> parts = StrUtil.split(path, File.separatorChar);
        String catalogue = parts.get(parts.size() - 2);

        // 没有二级目录不认为是正常文章,因为无法分类
        if (BLOG_DIR.equals(catalogue)) {
            return null;
        }

        // 转换内容为 html
        InformationBlock block = CommonMarkUtils.markdown2InformationBlock(FileUtil.readUtf8String(file));

        // 解析发布时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(block.getTime(), formatter);

        // 转换为 Article 对象
        Article article = new Article();
        article.setTitle(block.getTitle());
        article.setAuthor(block.getAuthor());
        article.setDateTime(localDateTime);
        article.setDateTimeString(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        article.setLabels(block.getLabels());
        article.setCatalogue(catalogue);
        article.setHtml(block.getHtml());
        article.setBrief(block.getBrief());
        return article;
    }

    protected Map<String, Integer> countArticleLabels(List<Article> articleList) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (Article article : articleList) {
            for (String label : article.getLabels()) {
                Integer count = map.get(label);
                if (count == null) {
                    count = 0;
                }
                count += 1;
                map.put(label, count);
            }
        }
        return map;
    }
}
