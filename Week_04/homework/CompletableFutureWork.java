import java.util.concurrent.CompletableFuture;

/**
 * @author: hfeng
 * @2020/11/10
 * @Description:
 */
public class CompletableFutureWork {


    private static volatile int result = 0;


    private static int sum() {
        result = fibo(36);
        return result;
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }

        return fibo(a - 1) + fibo(a - 2);
    }


    public static void main(String[] args) throws Exception {
//        Integer integer = CompletableFuture.supplyAsync(CompletableFutureWork::sum).get();
        CompletableFuture.runAsync(()->{
            sum();
        }).get();
        System.out.println(result);
    }
}
