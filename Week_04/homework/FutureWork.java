import java.util.concurrent.*;

/**
 * @author: hfeng
 * @2020/11/10
 * @Description:
 */
public class FutureWork {

    private static volatile int result = 0;

    private static int sum(){
        result = fibo(36);
        return result;
    }

    private static int fibo(int a) {
        if ( a < 2){
            return 1;
        }

        return fibo(a-1) + fibo(a-2);
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(() -> sum());
        try {
            System.out.println("异步计算结果为："+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
