package alioth.mrsheng.space;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import alioth.mrsheng.space.core.utils.XmlMapperFactory;
import alioth.mrsheng.space.core.wxaes.WXBizMsgCrypt;
import alioth.mrsheng.space.core.wxaes.WXBizMsgCryptFactory;

@SpringBootApplication
public class StartApplication extends SpringBootServletInitializer {

    @Bean
    public WXBizMsgCrypt wxBizMsgCrypt() throws Exception {
        return new WXBizMsgCryptFactory().getObject();
    }

    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapperFactory().getObject();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StartApplication.class);
    }
}
