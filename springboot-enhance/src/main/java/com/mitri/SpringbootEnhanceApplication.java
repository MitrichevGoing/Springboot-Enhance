package com.mitri;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class SpringbootEnhanceApplication implements CommandLineRunner{
	public static Logger logger = LoggerFactory.getLogger(SpringbootEnhanceApplication.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Resource(name = "jedisSecondPool")
	private JedisPool pool;


	public static void main(String[] args) {
		SpringApplication.run(SpringbootEnhanceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
				.setNameFormat("demo-pool-%d").build();
		ExecutorService singleThreadPool = new ThreadPoolExecutor(10, 20,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

		CompletableFuture.runAsync(() ->{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		},singleThreadPool);

		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "supplyAsync~~~2"+ Thread.currentThread().getName();
		}, singleThreadPool).whenComplete((it ,err) -> System.out.println(it));

		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "supplyAsync~~~3"+ Thread.currentThread().getName();
		},singleThreadPool).whenComplete((it,err) -> System.out.println(it))
										 .thenRun(() -> {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("supplyAsync~~~4"+ Thread.currentThread().getName());;
		});

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "supplyAsync~~~5" + Thread.currentThread().getName();
		},singleThreadPool);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "supplyAsync~~~6"+ Thread.currentThread().getName();
		},singleThreadPool);

		CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(future1, future2);

		System.out.println(objectCompletableFuture.get());

		System.out.println("test~~~"+ Thread.currentThread().getStackTrace()[1].getMethodName());

//		for (int x=0;x<3;x++){
//			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("testnew",
//					"test");
//
//			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//
//				@Override
//				public void onSuccess(SendResult<String, String> result) {
//					logger.info("sent topic={} data={} with offset={} partition={}", result.getProducerRecord().topic(), result.getProducerRecord().value(),
//							result.getRecordMetadata().offset(), result.getRecordMetadata().partition
//									());
//				}
//
//				@Override
//				public void onFailure(Throwable ex) {
//					logger.error("unable to send topic={}, data={}", "test", "test", ex);
//				}
//			});
//		}

		redisTemplate.execute((RedisCallback<Boolean>) connection -> {
			RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
			byte[] keys = serializer.serialize("hhh");
			byte[] values = serializer.serialize("打火机");
			connection.set(keys, values);
			Boolean expire = connection.expire(keys, 5);
			return expire;
		});

		redisTemplate.execute((RedisCallback<String>) connection -> {
			RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
			byte[] keys = serializer.serialize("hhh");
			byte[] values = connection.get(keys);
			return serializer.deserialize(values);
		});

		System.out.println(redisTemplate.opsForValue().get("hhh"));

		Jedis resource = pool.getResource();
		resource.set("xxx","nnn");
		resource.expire("xxx",5);
		System.out.println(resource.get("xxx"));

		Config config = new Config();
		//默认Kryo二进制序列化
//		config.setCodec(new KryoCodec())
//					//线程数
//					.setThreads(2*4)
//					.setNettyThreads(2*4);
//		String url = String.format("redis://%s:%d", redisConfigProperties.getHost(), redisConfigProperties.getPort());
		config.useSingleServer()
					.setAddress("redis://127.0.0.1:6379")
					.setDatabase(0);
		RedissonClient redissonClient = Redisson.create(config);



	}
}
