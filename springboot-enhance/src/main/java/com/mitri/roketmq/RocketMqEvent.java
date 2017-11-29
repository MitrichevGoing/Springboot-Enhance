package com.mitri.roketmq;

import java.io.UnsupportedEncodingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;

/**
 * <p>Title: Springboot-Enhance--com.mitri.roketmq.RocketMqEvent</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/7 上午12:46 </p>
 *
 * @author Potter
 * @version v2
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RocketMqEvent extends ApplicationEvent {
  private static final long serialVersionUID = -4468405250074063206L;

  private DefaultMQPushConsumer consumer;
  private MessageExt messageExt;
  private String topic;
  private String tag;
  private byte[] body;

  public RocketMqEvent(MessageExt msg,DefaultMQPushConsumer consumer) throws Exception {
    super(msg);
    this.topic = msg.getTopic();
    this.tag = msg.getTags();
    this.body = msg.getBody();
    this.consumer = consumer;
    this.messageExt = msg;
  }

  public String getMsg() {
    try {
      return new String(this.body,"utf-8");
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }

  public String getMsg(String code) {
    try {
      return new String(this.body,code);
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }
}
