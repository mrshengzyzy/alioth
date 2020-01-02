package alioth.mrsheng.space.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * Jackson工具类
 */
public class JacksonUtils {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.getObjectMapper();

    private static final XmlMapper XML_MAPPER = XmlMapperFactory.getXmlMapper();

    public static <T> T stringToBean(String json, Class<T> cls) throws IOException {
        if (null == json) {
            return null;
        }
        return OBJECT_MAPPER.readValue(beanToString(json), cls);
    }

    public static String beanToString(Object bean) throws JsonProcessingException {
        if (null == bean || bean instanceof String) {
            return (String) bean;
        }
        return OBJECT_MAPPER.writeValueAsString(bean);
    }

    public static <T> T xmlToBean(String xml, Class<T> cls) throws IOException {
        if (null == xml) {
            return null;
        }
        return XML_MAPPER.readValue(xml, cls);
    }

    public static String beanToXml(Object bean) throws JsonProcessingException {
        if (null == bean) {
            return null;
        }
        return XML_MAPPER.writeValueAsString(bean);
    }
}
