package thread0530;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:ThreadDemo1
 * Package:thread0530
 * Description:
 *
 * @Author:HP
 * @date:2021/5/30 9:23
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        //信号量
        Semaphore semaphore = new Semaphore(2);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,0, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1000));

        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "到达停车场");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //试图获取锁
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //获取到锁了
                    System.out.println(Thread.currentThread().getName() + "进入停车场");
                    int num = 1 + new Random().nextInt(5);
                    try {
                        Thread.sleep(num * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "离开停车场");

                    //释放锁
                    semaphore.release();
                }
            });
        };

    }
}
