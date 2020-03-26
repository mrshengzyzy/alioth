package alioth.mrsheng.space;

import alioth.mrsheng.space.core.utils.XmlMapperFactory;
import alioth.mrsheng.space.core.wxaes.WXBizMsgCrypt;
import alioth.mrsheng.space.core.wxaes.WXBizMsgCryptFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StartApplication {

    @Bean
    public WXBizMsgCrypt wxBizMsgCrypt() throws Exception {
        return new WXBizMsgCryptFactory().getObject();
    }

    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapperFactory().getObject();
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
