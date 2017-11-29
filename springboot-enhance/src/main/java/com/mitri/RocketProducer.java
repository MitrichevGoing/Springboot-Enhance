package com.mitri;

import java.util.Date;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * <p>Title: Springboot-Enhance--com.mitri.RocketProducer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/4 下午12:36 </p>
 *
 * @author Potter
 * @version v2
 */
public class RocketProducer {

  public static void main(String[] args) throws MQClientException {
    DefaultMQProducer producer = new DefaultMQProducer("defaultProducer");
    producer.setNamesrvAddr("localhost:9876");
    producer.setInstanceName("rmq-instanceaa");
    producer.start();
    try {
      for (int i = 0; i < 3; i++) {
        Message msg = new Message("PLANE_QUEUE",// topic
            "tag",// tag
            (new Date() + "Hello RocketMQ ,QuickStart" + i)
                .getBytes()// body
        );
        SendResult sendResult = producer.send(msg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    producer.shutdown();
  }

}
