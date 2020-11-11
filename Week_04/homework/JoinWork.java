/**
 * @author: hfeng
 * @2020/11/10
 * @Description:
 */
public class JoinWork {
    private static volatile int result = 0;

    private static void sum(){
        result = fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2){
            return 1;
        }

        return fibo(a-1) + fibo(a-2);
    }




    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            sum();
        });
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+result);
    }
}
