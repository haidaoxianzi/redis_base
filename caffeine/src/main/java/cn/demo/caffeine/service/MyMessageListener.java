package cn.demo.caffeine.service;

import cn.demo.caffeine.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyMessageListener implements MessageListener {

    @Autowired
    private UserService userService;

    //这里就是应用接收到了（要删除缓存的策略）： 这里就强制删除Caffeine中的缓存数据
    @Override
    public void onMessage(Message message, byte[] pattern) {
//        String channel = new String(message.getChannel());
//        String body = new String(message.getBody());
//        if(!body.equals("")){
//            log.info("deleteCache");
//            userService.deleteCache(body);
//        }
    }
}
