/**
 * @author: hfeng
 * @2020/11/10
 * @Description:
 */
public class WaitAndNotifyWork extends Thread{

    private static volatile int result = 0;
    private static WaitAndNotifyWork work = new WaitAndNotifyWork();

    private synchronized int sum(){
        work.notify();
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2){
            return 1;
        }

        return fibo(a-1) + fibo(a-2);
    }


    public static void main(String[] args) {

        work.start();
        try {
            synchronized (work){
                work.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+result);
    }

    @Override
    public void run() {
        result = sum();
    }
}
