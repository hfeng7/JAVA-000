import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: hfeng
 * @2020/10/18
 * @Description:
 */
public class MyClassloader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        File f = new File(this.getClass().getResource("/Hello.xlass").getPath());
        int length = (int)f.length();
        byte[] bytes = new byte[length];
        try {
            new FileInputStream(f).read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return super.findClass(name);
        }
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)(255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }


    public static void main(String[] args) {


        try {
            //获取加载的类
            Class<?> aClass = new MyClassloader().findClass("Hello");
            try {
                //调用hello方法
                Method hello = aClass.getDeclaredMethod("hello");
                hello.setAccessible(true);
                //调用指定实例方法
                hello.invoke(aClass.newInstance());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
