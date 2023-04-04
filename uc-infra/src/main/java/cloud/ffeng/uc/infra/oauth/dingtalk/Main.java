package cloud.ffeng.uc.infra.oauth.dingtalk;

import cloud.ffeng.common.util.UuidUtil;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final int SIZE = 100_0000;

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(16);
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        StopWatch stopWatch = new StopWatch();
        File file = new File(String.format("D:\\test%s.csv", UuidUtil.generateUuid()));
        FileOutputStream fos = new FileOutputStream(file);

        stopWatch.start();
        for (int i = 0; i < SIZE; i++) {
            executorService.submit(()->{
                String result = new Random().nextInt(10000) +
                        "," + UuidUtil.generateUuid() + ',' +
                        UuidUtil.generateUuid() + ',' +
                        UuidUtil.generateUuid() + ',' +
                        UuidUtil.generateUuid() + ',' +
                        UuidUtil.generateUuid() + ',' +
                        "abc" + '\n';

                try {
                    synchronized (Main.class){
                        fos.write(result.getBytes(StandardCharsets.UTF_8));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        stopWatch.split();
        long seconds = stopWatch.getSplitTime() / 1000;
        System.out.println("finished! " + seconds);
        fos.close();
    }
}
