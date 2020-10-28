#### 作业1（根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结）

##### 串行GC

单线程的GC收集器，进行垃圾回收，必须停止业务（STW），GC暂停时间长；内存小，GC次数多。新生代使用标记-复制算法，老年代使用标记-整理算法

##### 并行GC

类似于SerialGC的多线程版。主要在于提高吞吐量，并行GC默认线程数为当前CPU核数，可通过-XX:ParallelGCThreads=N来制定

##### CMS

多线程并发标记清除，降低延迟。适用于低延迟优先的系统

##### G1

取消分代，使用多个内存区域做增量回收，进一步降低延迟。Eden区（标记-复制）存活区老年区（标记-复制-整理）。大内存的推荐使用

##### ZGC

目前只在linux系统适用。是通过着色和读屏障，来实现毫米级别的延迟，做到线性可扩展。能够保证GC最大停顿时间在10ms内，内存从小到大都可以支持

##### 常用的 GC 组合

- Serial + Serial Old ：实现单线程的低延迟垃圾回收机制

- ParNew + CMS：实现多线程的低延迟垃圾回收机制

- Parallel Scavenge + Parallel Old ：实现高多线程的高吞吐量垃圾回收机制





#### 作业2 （写一段代码，使用 HttpClient 或 OkHttp 访问http://localhost:8801，代码提交到 github）

```Java
package com.homework.nio;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author: hfeng
 * @2020/10/28
 * @Description:
 */
public class HttpClient {

    public static void main(String[] args) {
        http();
    }

    private static void http(){

        String url = "http://localhost:8088/api/hello";

        //创建client客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //get请求
        HttpGet httpGet = new HttpGet(url);
        //设置连接
        httpGet.setHeader("Connection","keep-alive");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("输出结果："+EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

```

```java
<!-- maven依赖 --> 
<dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.12</version>
</dependency>
```

