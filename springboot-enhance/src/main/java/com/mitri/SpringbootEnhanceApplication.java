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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class SpringbootEnhanceApplication implements CommandLineRunner{
	public static Logger logger = LoggerFactory.getLogger(SpringbootEnhanceApplication.class);


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

		System.out.println("test~~~"+ Thread.currentThread().getName());

	}
}
