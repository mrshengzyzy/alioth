package alioth.mrsheng.space.service.impl;

import alioth.mrsheng.space.core.utils.SimpleRestClient;
import alioth.mrsheng.space.service.ISendMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SendMessageServiceImpl implements ISendMessageService {

    @Resource
    private SimpleRestClient simpleRestClient;

    @Override
    public void sendTextMessage(String token, String message) {

    }
}
