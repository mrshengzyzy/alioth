package alioth.mrsheng.space.business.event;

/**
 * Event消息抽象类
 */
public abstract class AbstractEventBusiness implements IEventBusiness {

    /**
     * 从报文中提取Event
     */
    private static final String EVENT_START = "<Event><![CDATA[";
    private static final String EVENT_END = "]]></Event>";

    String getEvent(String realClearXml) {
        return realClearXml.substring(realClearXml.indexOf(EVENT_START) + 16, realClearXml.indexOf(EVENT_END));
    }

}
