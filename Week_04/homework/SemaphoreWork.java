import java.util.concurrent.Semaphore;

/**
 * @author: hfeng
 * @2020/11/10
 * @Description:
 */
public class SemaphoreWork {
    private static volatile int result = 0;

    private static Semaphore semaphore = new Semaphore(0);

    private static void sum(){
        result = fibo(36);
        semaphore.release();
    }

    private static int fibo(int a) {
        if ( a < 2){
            return 1;
        }

        return fibo(a-1) + fibo(a-2);
    }

    public static void main(String[] args) {
        new Thread(()->{
            sum();
        }).start();

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+result);

    }
}
