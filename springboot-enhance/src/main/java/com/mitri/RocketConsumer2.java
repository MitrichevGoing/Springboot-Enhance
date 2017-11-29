package com.mitri;

import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * <p>Title: Springboot-Enhance--com.mitri.RocketProducer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/4 下午12:00 </p>
 *
 * @author Potter
 * @version v2
 */
public class RocketConsumer2 {

  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
        "default");

    consumer.setNamesrvAddr("localhost:9876");
    consumer.setInstanceName("rmq-instancesss");
    consumer.subscribe("PLANE_QUEUE", "tag");
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

    consumer.registerMessageListener(new MessageListenerConcurrently() {
//      @Override
//      public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list,
//          ConsumeOrderlyContext consumeOrderlyContext) {
//        for (MessageExt msg : list) {
//          System.out.println(new String(msg.getBody()));
//        }
//        return ConsumeOrderlyStatus.SUCCESS;
//      }

      @Override
      public ConsumeConcurrentlyStatus consumeMessage(
          List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
          System.out.println(new String(msg.getBody())+ "time:"+ String.valueOf(msg.getBornTimestamp()));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
      }
    });
    consumer.start();
    System.out.println("Consumer Started.");
  }

}
