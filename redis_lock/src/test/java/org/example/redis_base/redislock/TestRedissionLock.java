package org.example.redis_base.redislock;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class TestRedissionLock {

    private int count = 0;
    @Autowired
    private RedissonClient redisson;

    @Test
    public void testLockWithDog() throws InterruptedException {
        int clientCount =3;
        RLock lock = redisson.getLock("RD-lock");

        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        for (int i = 0;i<clientCount;i++){
            Long start = System.currentTimeMillis();
            //todo 这里要指定线程池，不要用默认的forkjoinPool
            executorService.execute(() -> {
                try {
                    //lock.lock(30, TimeUnit.SECONDS);
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+"准备进行累加。");
                    Thread.sleep(31000);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                Long end = System.currentTimeMillis();
                log.info("end-start={}s", (end - start) / 1000);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        System.out.println(count);
    }
}
