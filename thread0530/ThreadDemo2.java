package thread0530;

import java.util.Random;
import java.util.concurrent.*;

/**
 * ClassName:ThreadDemo1
 * Package:thread0530
 * Description:
 *
 * @Author:HP
 * @date:2021/5/30 9:23
 */
public class ThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        //计数器
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 1; i < 6; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +
                            " 开始起跑");
                    try {
                        Thread.sleep(finalI * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +
                            " 到达终点");
                    // 将计数器 -1
                    latch.countDown();
                }
            }).start();
        }
        // 阻塞等待
        latch.await();
        System.out.println("所有人都到达终点了，公布排名");
    }
}
