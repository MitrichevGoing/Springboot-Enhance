package com.mitri.zk;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * <p>Title: Springboot-Enhance--com.mitri.zk.KafkaTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/15 上午11:09 </p>
 *
 * @author Potter
 * @version v2
 */
@Service
public class KafkaTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @KafkaListener(topics = "test")
  public void test(ConsumerRecord<String, String> record){
    logger.info("has consume msg {}", record.value());
  }

}
