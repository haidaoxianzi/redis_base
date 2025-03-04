package org.example.redis_base.redislock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Auther: gina
 * @Date: 2025-01-19
 * @Description:
 */
@SpringBootTest
@Slf4j
public class Test1 {
    @Test
    void test(){
        Object o="123a";
        log.info(o.toString());
    }
}
