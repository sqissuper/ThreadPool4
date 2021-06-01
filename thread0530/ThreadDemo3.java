package thread0530;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * ClassName:ThreadDemo1
 * Package:thread0530
 * Description:
 *
 * @Author:HP
 * @date:2021/5/30 9:23
 */
public class ThreadDemo3 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("执行了Runnable");
            }
        });

        for (int i = 1; i < 11; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "开始起跑");
                    try {
                        Thread.sleep(200 * finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        //计数器-1，判断计数器是否为 0
                        System.out.println(Thread.currentThread().getName() + "等待人");
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    //已经有一组比赛结束了
                    System.out.println(Thread.currentThread().getName() + "执行结束");
                }
            }).start();
        }
    }
}
