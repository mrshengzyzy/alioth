package alioth.mrsheng.space.core.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 静态初始化ObjectMapper
 * 保证Spring容器中和用{@link #getObjectMapper()}获取到的实例为同一个
 */
public class ObjectMapperFactory implements FactoryBean<ObjectMapper> {

    private static ObjectMapper objectMapper;

    private static synchronized void init() {
        if (null == objectMapper) {
            objectMapper = Jackson2ObjectMapperBuilder.json().build();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        }
    }

    public static ObjectMapper getObjectMapper() {
        if (null == objectMapper) {
            init();
        }

        return objectMapper;
    }

    @Override
    public ObjectMapper getObject() {
        if (null == objectMapper) {
            init();
        }
        return objectMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return ObjectMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
