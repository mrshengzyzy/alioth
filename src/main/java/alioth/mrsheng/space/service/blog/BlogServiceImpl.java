package alioth.mrsheng.space.service.blog;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
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

    @Override
    public List<Article> articleList() {
        List<Article> articleList = new ArrayList<>();
        Article a = new Article("AAA", "2021-02-07 17:28:20", "This is *Sparta*");
        Article a1 = new Article("BBB", "2021-02-07 17:28:20", "This is *Sparta*");
        Article a2 = new Article("CCC", "2021-02-07 17:28:20", "This is *Sparta*");
        Article a3 = new Article("DDD", "2021-02-07 17:28:20", "This is *Sparta*");
        Article a4 = new Article("EEE", "2021-02-07 17:28:20", "This is *Sparta*");
        articleList.add(a);
        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);
        articleList.add(a4);
        return articleList;
    }

    @Override
    public Article article(String title) {
        return null;
    }

    private List<Article> _cache() {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("posts/**");
            for (Resource resource : resources) {
                File file = resource.getFile();
                if (file.isFile()) {
                    String path = file.getPath();
                    List<String> parts = StrUtil.split(path, File.separatorChar);
                    String dir = parts.get(parts.size() - 2);
                    String content = FileUtil.readUtf8String(file);
                    System.out.println(dir + ":" + content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
