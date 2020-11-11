import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: hfeng
 * @2020/11/10
 * @Description:
 */
public class FutureTaskWork {

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
        FutureTask<Integer> futureTask = new FutureTask(FutureTaskWork::sum);
        new Thread(futureTask).start();

        try {
            System.out.println("异步计算结果为："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
