package alioth.mrsheng.space.service.blog;

import alioth.mrsheng.space.core.CommonMarkUtils;
import alioth.mrsheng.space.domain.blog.Article;
import alioth.mrsheng.space.domain.blog.Link;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.io.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl extends AbstractBlogSupport implements IBlogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);

    // 友链缓存,默认 7 天过期
    private final TimedCache<String, List<Link>> linkCache = CacheUtil.newTimedCache(7 * 86400 * 1000);
    public final String linkCacheKey = "linkKey";

    @Override
    public List<Article> queryArticles() throws Exception {
        List<Article> articleList = new ArrayList<>();
        List<File> fileList = loadFiles(BLOG_DIR + "/**");
        for (File file : fileList) {
            Article article = fileToArticle(file);
            if (article != null) {
                articleList.add(article);
            }
        }
        return articleList;
    }

    @Override
    public List<Link> queryLinks() throws Exception {

        // 从缓存中拿数据
        List<Link> linkListCache = linkCache.get(linkCacheKey);
        if (linkListCache != null && linkListCache.size() > 0) {
            LOGGER.info("query links from cache");
            return linkListCache;
        }

        // 重新计算
        List<Link> linkList = new ArrayList<>();

        // 理论上只能读取到一个文件
        List<File> fileList = loadFiles(BLOG_DIR + "/" + Link.LinkFile);
        if (fileList.size() != 1) {
            LOGGER.error("query multi links file");
            return linkList;
        }

        // 解析为 html
        File file = fileList.get(0);
        String html = CommonMarkUtils.markdown2Html(FileUtil.readUtf8String(file));

        // 结构化为 document 对象
        Document doc = Jsoup.parseBodyFragment(html);

        // 读取所有的 a 标签并转换为 Link 对象
        Elements elements = doc.getElementsByTag("a");
        for (Element e : elements) {
            String href = e.attr("href");
            String text = e.text();
            linkList.add(new Link(text, href));
        }

        // 放入缓存
        linkCache.put(linkCacheKey, linkList);
        LOGGER.info("query links put cache");

        return linkList;
    }
}
