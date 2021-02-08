package alioth.mrsheng.space.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceLoader {

    @Test
    public void testFile() throws IOException {
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
    }
}
