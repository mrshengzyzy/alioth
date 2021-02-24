package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BlogServiceImpl extends AbstractBlogServiceImpl implements IBlogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Override
    public List<Article> articleList(String catalogue) throws IOException {
        List<Article> articleList = new ArrayList<>();
        String locationPattern = catalogueToLocationPattern(catalogue);
        List<File> fileList = loadFiles(locationPattern);
        for (File file : fileList) {
            articleList.add(fileToArticle(file));
        }
        return articleList;
    }

    @Override
    public Article article(String catalogue, String title) throws IOException {
        String locationPattern = BLOG_DIR + "/" + catalogue + "/" + title + ".md";
        return fileToArticle(loadFile(locationPattern));
    }

    @Override
    public synchronized Map<String, Integer> countLabels(String catalogue) throws IOException {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        List<Article> articleList = articleList(catalogue);
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
