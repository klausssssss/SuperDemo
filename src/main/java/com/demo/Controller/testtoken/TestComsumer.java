package com.demo.Controller.testtoken;

import com.demo.Common.RocketMQEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestComsumer {
    @EventListener(condition = "#event.msgs[0].topic=='TopicTest1' && #event.msgs[0].tags=='TagA'")
    public void rocketmqMsgListen(RocketMQEvent event) {
//      DefaultMQPushConsumer consumer = event.getConsumer();
        try {
            System.out.println("监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
            // TODO 进行业务处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
