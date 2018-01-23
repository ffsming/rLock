package com.lock;

import com.lock.util.rLock.RLock;
import com.lock.util.redis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@ImportResource(locations = {"classpath:config/spring-jedis.xml" })
public class RlockApplication {

	public static void main(String[] args) {
		SpringApplication.run(RlockApplication.class, args);
		log.info("启动成功");
	}
	@RestController
	public class test{
		@RLock(name="dd" , expireTime = 10)
		@RequestMapping("/test")
		public void test(){
			log.info("3232323");
			return;
		}
	}
}
