package org.example.redis_base.redislock;


import lombok.extern.slf4j.Slf4j;
import org.example.redis_base.lock.idl.RedisDistLockWithDog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 手动实现redis分布式锁
 */
@SpringBootTest
@Slf4j
public class TestRedisDistLockWithDog {

    @Resource
    private RedisDistLockWithDog redisDistLockWithDog;
    private int count = 0;

    @Test
    public void testLockWithDog() throws InterruptedException {
        int clientCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        for (int i = 0; i < clientCount; i++) {
            int finalI = i;
            executorService.execute(() -> {
                long start = 0;
                try {
                    redisDistLockWithDog.lock(); //锁的有效时间1秒
                    start = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName() + "准备进行累加。");
                    Thread.sleep(2000);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    long end = System.currentTimeMillis();
                    log.info("end-start={}s,i={}", (end - start) / 1000, finalI);
                    redisDistLockWithDog.unlock();
                }

                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(count);
    }

    @Test
    public void testTryLock2() {
        int clientCount = 1000;
        for (int i = 0; i < clientCount; i++) {
            if (redisDistLockWithDog.tryLock()) {
                log.info("已获得锁！i={}", i);
                redisDistLockWithDog.unlock();
            } else {
                System.out.println("未能获得锁！");
            }
        }
    }

}
