package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.domain.blog.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl extends AbstractBlogSupport implements IBlogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Override
    public List<Article> articleList(String dir) throws IOException {
        List<Article> articleList = new ArrayList<>();
        List<File> fileList = loadFiles(dir);
        for (File file : fileList) {
            articleList.add(fileToArticle(file));
        }
        return articleList;
    }

    @Override
    public Article articleDetail(String location) throws IOException {
        return fileToArticle(loadFile(location));
    }

}
