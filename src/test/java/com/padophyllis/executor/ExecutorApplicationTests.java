package com.padophyllis.executor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.ExecutorService;

@SpringBootTest
class ExecutorApplicationTests {

    @Autowired
    private ExecutorService executorService;

    @Test
    public void helloThread(){
        executorService.execute(() -> {
            for (int i = 0;i < 100;i++){
                System.out.println("111");
            }
        });
        executorService.execute(() -> {
            for (int i = 0;i < 100;i++){
                System.out.println("222");
            }
        });
    }


    /**
     * 异步方法，如果@Async加在类的上面，则整个类中的方法都是异步的
     */
    @Test
    @Async
    public void print(){
        for (int i = 0;i < 100;i++){
            System.out.println(i);
        }
    }

}
