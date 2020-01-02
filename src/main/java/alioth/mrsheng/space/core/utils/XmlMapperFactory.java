package alioth.mrsheng.space.core.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.FactoryBean;

/**
 * 静态初始化XmlMapper
 * 保证Spring容器中和用{@link #getXmlMapper()}获取到的实例为同一个
 */
public class XmlMapperFactory implements FactoryBean<XmlMapper> {

    private static XmlMapper xmlMapper;

    private static synchronized void init() {
        if (null == xmlMapper) {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            xmlMapper = new XmlMapper(module);
            xmlMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            xmlMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        }
    }

    public static XmlMapper getXmlMapper() {
        if (null == xmlMapper) {
            init();
        }
        return xmlMapper;
    }

    @Override
    public XmlMapper getObject() {
        if (null == xmlMapper) {
            init();
        }
        return xmlMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return XmlMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
