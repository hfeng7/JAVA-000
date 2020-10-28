学习笔记



##### 使用GCLogAnalysis.java自己演练一遍串行/并行/CMS/G1的案例



1、默认并行GC

```java
$ java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
  
正在执行...
执行结束！共生成对象次数:6614
```

```java
Java HotSpot(TM) 64-Bit Server VM (25.211-b12) for bsd-amd64 JRE (1.8.0_211-b12), built on Apr  1 2019 20:53:18 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 8388608k(459980k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
2020-10-28T12:50:50.528-0800: 0.132: [GC (Allocation Failure) [PSYoungGen: 33280K->5119K(38400K)] 33280K->10257K(125952K), 0.0035789 secs] [Times: user=0.00 sys=0.01, real=0.01 secs] 
2020-10-28T12:50:50.539-0800: 0.143: [GC (Allocation Failure) [PSYoungGen: 38399K->5110K(71680K)] 43537K->24369K(159232K), 0.0084558 secs] [Times: user=0.01 sys=0.02, real=0.01 secs] 
2020-10-28T12:50:50.567-0800: 0.172: [GC (Allocation Failure) [PSYoungGen: 71670K->5112K(71680K)] 90929K->45535K(159232K), 0.0080168 secs] [Times: user=0.01 sys=0.02, real=0.01 secs] 
2020-10-28T12:50:50.584-0800: 0.188: [GC (Allocation Failure) [PSYoungGen: 71672K->5101K(138240K)] 112095K->70644K(225792K), 0.0093346 secs] [Times: user=0.01 sys=0.03, real=0.01 secs] 
2020-10-28T12:50:50.593-0800: 0.198: [Full GC (Ergonomics) [PSYoungGen: 5101K->0K(138240K)] [ParOldGen: 65542K->68925K(139264K)] 70644K->68925K(277504K), [Metaspace: 2706K->2706K(1056768K)], 0.0091690 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T12:50:50.637-0800: 0.242: [GC (Allocation Failure) [PSYoungGen: 133120K->5112K(138240K)] 202045K->116030K(277504K), 0.0142793 secs] [Times: user=0.02 sys=0.04, real=0.02 secs] 
2020-10-28T12:50:50.651-0800: 0.256: [Full GC (Ergonomics) [PSYoungGen: 5112K->0K(138240K)] [ParOldGen: 110918K->107186K(202240K)] 116030K->107186K(340480K), [Metaspace: 2706K->2706K(1056768K)], 0.0111335 secs] [Times: user=0.04 sys=0.00, real=0.01 secs] 
2020-10-28T12:50:50.679-0800: 0.283: [GC (Allocation Failure) [PSYoungGen: 132738K->45084K(279552K)] 239924K->152271K(481792K), 0.0150449 secs] [Times: user=0.02 sys=0.04, real=0.02 secs] 
2020-10-28T12:50:50.753-0800: 0.357: [GC (Allocation Failure) [PSYoungGen: 275996K->58354K(289280K)] 383183K->216865K(491520K), 0.0738350 secs] [Times: user=0.04 sys=0.05, real=0.07 secs] 
2020-10-28T12:50:50.826-0800: 0.431: [Full GC (Ergonomics) [PSYoungGen: 58354K->0K(289280K)] [ParOldGen: 158510K->187222K(300544K)] 216865K->187222K(589824K), [Metaspace: 2706K->2706K(1056768K)], 0.0389492 secs] [Times: user=0.05 sys=0.01, real=0.04 secs] 
2020-10-28T12:50:50.907-0800: 0.512: [GC (Allocation Failure) [PSYoungGen: 230912K->74787K(493056K)] 418134K->262009K(793600K), 0.1018177 secs] [Times: user=0.02 sys=0.04, real=0.10 secs] 
2020-10-28T12:50:51.121-0800: 0.725: [GC (Allocation Failure) [PSYoungGen: 473635K->102906K(501760K)] 660857K->349867K(802304K), 0.1910530 secs] [Times: user=0.06 sys=0.08, real=0.19 secs] 
2020-10-28T12:50:51.312-0800: 0.916: [Full GC (Ergonomics) [PSYoungGen: 102906K->0K(501760K)] [ParOldGen: 246961K->263283K(393728K)] 349867K->263283K(895488K), [Metaspace: 2706K->2706K(1056768K)], 0.0443108 secs] [Times: user=0.08 sys=0.01, real=0.04 secs] 
2020-10-28T12:50:51.406-0800: 1.011: [GC (Allocation Failure) [PSYoungGen: 398848K->125836K(521216K)] 662131K->389119K(914944K), 0.1029890 secs] [Times: user=0.05 sys=0.06, real=0.10 secs] 
Heap
 PSYoungGen      total 521216K, used 141029K [0x0000000795580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 377344K, 4% used [0x0000000795580000,0x0000000796456680,0x00000007ac600000)
  from space 143872K, 87% used [0x00000007b7380000,0x00000007bee630b8,0x00000007c0000000)
  to   space 160768K, 0% used [0x00000007ac600000,0x00000007ac600000,0x00000007b6300000)
 ParOldGen       total 393728K, used 263283K [0x0000000740000000, 0x0000000758080000, 0x0000000795580000)
  object space 393728K, 66% used [0x0000000740000000,0x000000075011cf30,0x0000000758080000)
 Metaspace       used 2712K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
```

- Memory: 4k page, physical 8388608k(459980k free)：内存页4k，物理内存大小8388608k（8g）

- -XX:InitialHeapSize=134217728：初始Heap大小128mb，默认物理内存1/64

-  -XX:MaxHeapSize=2147483648 ：最大Heap大小2g，默认物理内存1/4

-  -XX:+UseCompressedClassPointers -XX:+UseCompressedOops ：类/对象指针压缩

- -XX:+UseParallelGC ：并行GC

- 出现10次young Gc和4次full Gc

- [GC (Allocation Failure) [PSYoungGen: 33280K->5119K(38400K)] 33280K->10257K(125952K), 0.0035789 secs] [Times: user=0.00 sys=0.01, real=0.01 secs] ：分配内存空间失败，出现第一次Young GC，Young区37.5MB，从33280k（32.5mb）减少到5119k（5mb），回收85%。此时Heap125952k（123mb），Old区计算得85.5MB。从33280k（32.5mb，Eden区）减少到10257k（10mb），回收70%空间，此次Young GC有5138k（10257k-5119k）晋升Old区。GC用户态（user）时间0.00，内核态（sys）0.01，实际消耗（real）0.01

- [Full GC (Ergonomics) [PSYoungGen: 5101K->0K(138240K)] [ParOldGen: 65542K->68925K(139264K)] 70644K->68925K(277504K), [Metaspace: 2706K->2706K(1056768K)], 0.0091690 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] ：收集器自动调解gc暂停时间和吞吐量之间的平衡，出现full GC，Young区138240k（135mb），从5101k-0k，回收100%；Old大小为139264k（136mb），从65542k（64mb）到68925k（67mb），新增了3mb的对象。此时Heap277504k（271mb=135+136），full GC从70644k（69mb）到68925k（67mb），回收10%。Meta区经过GC后，容量不变

- 下面是JVM推出前Heap内存的使用情况

  

2、并行GC设置内存256mb

```java
$ java -Xms256m -Xmx256m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
  
  
  正在执行...
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:47)
	at GCLogAnalysis.main(GCLogAnalysis.java:24)
```

```
Java HotSpot(TM) 64-Bit Server VM (25.211-b12) for bsd-amd64 JRE (1.8.0_211-b12), built on Apr  1 2019 20:53:18 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 8388608k(261224k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=268435456 -XX:MaxHeapSize=268435456 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
2020-10-28T15:19:08.058-0800: 0.253: [GC (Allocation Failure) [PSYoungGen: 64965K->10729K(76288K)] 64965K->25059K(251392K), 0.0085470 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:19:08.082-0800: 0.276: [GC (Allocation Failure) [PSYoungGen: 76262K->10749K(76288K)] 90592K->46268K(251392K), 0.0097836 secs] [Times: user=0.01 sys=0.02, real=0.01 secs] 
2020-10-28T15:19:08.099-0800: 0.294: [GC (Allocation Failure) [PSYoungGen: 76285K->10744K(76288K)] 111804K->68665K(251392K), 0.0088619 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:19:08.116-0800: 0.310: [GC (Allocation Failure) [PSYoungGen: 76280K->10736K(76288K)] 134201K->92790K(251392K), 0.0108833 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-10-28T15:19:08.136-0800: 0.330: [GC (Allocation Failure) [PSYoungGen: 76272K->10738K(76288K)] 158326K->115842K(251392K), 0.0100085 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-10-28T15:19:08.154-0800: 0.348: [GC (Allocation Failure) [PSYoungGen: 76067K->10737K(40448K)] 181171K->132294K(215552K), 0.0084336 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:19:08.166-0800: 0.361: [GC (Allocation Failure) [PSYoungGen: 39976K->14159K(58368K)] 161533K->140296K(233472K), 0.0041641 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-28T15:19:08.175-0800: 0.369: [GC (Allocation Failure) [PSYoungGen: 43696K->20698K(58368K)] 169833K->149054K(233472K), 0.0036859 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2020-10-28T15:19:08.182-0800: 0.377: [GC (Allocation Failure) [PSYoungGen: 50389K->28411K(58368K)] 178744K->158961K(233472K), 0.0037256 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-10-28T15:19:08.190-0800: 0.384: [GC (Allocation Failure) [PSYoungGen: 57983K->17433K(58368K)] 188533K->165201K(233472K), 0.0079205 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
2020-10-28T15:19:08.198-0800: 0.392: [Full GC (Ergonomics) [PSYoungGen: 17433K->0K(58368K)] [ParOldGen: 147767K->134866K(175104K)] 165201K->134866K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0188050 secs] [Times: user=0.04 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.220-0800: 0.415: [GC (Allocation Failure) [PSYoungGen: 29626K->9057K(58368K)] 164493K->143923K(233472K), 0.0012628 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-28T15:19:08.225-0800: 0.420: [GC (Allocation Failure) [PSYoungGen: 38655K->7438K(58368K)] 173522K->151027K(233472K), 0.0017295 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2020-10-28T15:19:08.230-0800: 0.425: [GC (Allocation Failure) [PSYoungGen: 36966K->9195K(58368K)] 180554K->159878K(233472K), 0.0022836 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
2020-10-28T15:19:08.232-0800: 0.427: [Full GC (Ergonomics) [PSYoungGen: 9195K->0K(58368K)] [ParOldGen: 150682K->149735K(175104K)] 159878K->149735K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0146946 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-10-28T15:19:08.251-0800: 0.445: [Full GC (Ergonomics) [PSYoungGen: 29696K->0K(58368K)] [ParOldGen: 149735K->158363K(175104K)] 179431K->158363K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0168654 secs] [Times: user=0.04 sys=0.00, real=0.01 secs] 
2020-10-28T15:19:08.271-0800: 0.466: [Full GC (Ergonomics) [PSYoungGen: 29553K->0K(58368K)] [ParOldGen: 158363K->162925K(175104K)] 187917K->162925K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0184065 secs] [Times: user=0.05 sys=0.01, real=0.02 secs] 
2020-10-28T15:19:08.300-0800: 0.494: [Full GC (Ergonomics) [PSYoungGen: 29373K->0K(58368K)] [ParOldGen: 162925K->170380K(175104K)] 192299K->170380K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0171069 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-10-28T15:19:08.321-0800: 0.516: [Full GC (Ergonomics) [PSYoungGen: 29654K->1559K(58368K)] [ParOldGen: 170380K->174889K(175104K)] 200035K->176449K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0190342 secs] [Times: user=0.05 sys=0.01, real=0.02 secs] 
2020-10-28T15:19:08.343-0800: 0.538: [Full GC (Ergonomics) [PSYoungGen: 29692K->5373K(58368K)] [ParOldGen: 174889K->175015K(175104K)] 204582K->180388K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0181019 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.365-0800: 0.559: [Full GC (Ergonomics) [PSYoungGen: 29561K->6530K(58368K)] [ParOldGen: 175015K->174840K(175104K)] 204577K->181371K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0173956 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.385-0800: 0.580: [Full GC (Ergonomics) [PSYoungGen: 29696K->10258K(58368K)] [ParOldGen: 174840K->174655K(175104K)] 204536K->184914K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0216415 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.410-0800: 0.604: [Full GC (Ergonomics) [PSYoungGen: 29547K->13062K(58368K)] [ParOldGen: 174655K->174678K(175104K)] 204202K->187741K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0173409 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-10-28T15:19:08.430-0800: 0.624: [Full GC (Ergonomics) [PSYoungGen: 29579K->16084K(58368K)] [ParOldGen: 174678K->174438K(175104K)] 204257K->190523K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0176214 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-10-28T15:19:08.449-0800: 0.644: [Full GC (Ergonomics) [PSYoungGen: 29355K->17608K(58368K)] [ParOldGen: 174438K->175103K(175104K)] 203794K->192712K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0226234 secs] [Times: user=0.07 sys=0.00, real=0.03 secs] 
2020-10-28T15:19:08.474-0800: 0.668: [Full GC (Ergonomics) [PSYoungGen: 29696K->18432K(58368K)] [ParOldGen: 175103K->174966K(175104K)] 204799K->193398K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0205478 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.496-0800: 0.691: [Full GC (Ergonomics) [PSYoungGen: 29692K->18972K(58368K)] [ParOldGen: 174966K->174782K(175104K)] 204658K->193755K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0183091 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.516-0800: 0.710: [Full GC (Ergonomics) [PSYoungGen: 29595K->19339K(58368K)] [ParOldGen: 174782K->174565K(175104K)] 204378K->193905K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0180969 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.535-0800: 0.730: [Full GC (Ergonomics) [PSYoungGen: 29405K->20804K(58368K)] [ParOldGen: 174565K->174814K(175104K)] 203971K->195619K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0201013 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.556-0800: 0.751: [Full GC (Ergonomics) [PSYoungGen: 29602K->20906K(58368K)] [ParOldGen: 174814K->175053K(175104K)] 204416K->195959K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0191344 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.577-0800: 0.771: [Full GC (Ergonomics) [PSYoungGen: 29234K->21510K(58368K)] [ParOldGen: 175053K->174838K(175104K)] 204287K->196348K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0210931 secs] [Times: user=0.08 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.599-0800: 0.794: [Full GC (Ergonomics) [PSYoungGen: 29404K->23225K(58368K)] [ParOldGen: 174838K->174514K(175104K)] 204242K->197740K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0176887 secs] [Times: user=0.06 sys=0.01, real=0.02 secs] 
2020-10-28T15:19:08.618-0800: 0.812: [Full GC (Ergonomics) [PSYoungGen: 29663K->23325K(58368K)] [ParOldGen: 174514K->175082K(175104K)] 204177K->198407K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0181428 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.637-0800: 0.831: [Full GC (Ergonomics) [PSYoungGen: 29589K->25337K(58368K)] [ParOldGen: 175082K->174587K(175104K)] 204671K->199925K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0176972 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.655-0800: 0.850: [Full GC (Ergonomics) [PSYoungGen: 29667K->27076K(58368K)] [ParOldGen: 174587K->174404K(175104K)] 204254K->201481K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0200586 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.676-0800: 0.870: [Full GC (Ergonomics) [PSYoungGen: 29645K->26873K(58368K)] [ParOldGen: 174404K->175081K(175104K)] 204050K->201955K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0180481 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.694-0800: 0.889: [Full GC (Ergonomics) [PSYoungGen: 29673K->27453K(58368K)] [ParOldGen: 175081K->174989K(175104K)] 204755K->202442K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0111271 secs] [Times: user=0.04 sys=0.00, real=0.01 secs] 
2020-10-28T15:19:08.706-0800: 0.900: [Full GC (Ergonomics) [PSYoungGen: 29166K->28383K(58368K)] [ParOldGen: 174989K->174491K(175104K)] 204156K->202874K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0103928 secs] [Times: user=0.04 sys=0.00, real=0.01 secs] 
2020-10-28T15:19:08.716-0800: 0.911: [Full GC (Ergonomics) [PSYoungGen: 29587K->28348K(58368K)] [ParOldGen: 174491K->174491K(175104K)] 204078K->202839K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0024752 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-28T15:19:08.719-0800: 0.914: [Full GC (Ergonomics) [PSYoungGen: 29535K->28291K(58368K)] [ParOldGen: 174491K->174311K(175104K)] 204026K->202602K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0218816 secs] [Times: user=0.08 sys=0.00, real=0.03 secs] 
2020-10-28T15:19:08.741-0800: 0.936: [Full GC (Ergonomics) [PSYoungGen: 29588K->28512K(58368K)] [ParOldGen: 174311K->174309K(175104K)] 203900K->202821K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0069481 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
2020-10-28T15:19:08.748-0800: 0.943: [Full GC (Ergonomics) [PSYoungGen: 29667K->28507K(58368K)] [ParOldGen: 174309K->174953K(175104K)] 203976K->203461K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0134679 secs] [Times: user=0.04 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.762-0800: 0.957: [Full GC (Ergonomics) [PSYoungGen: 29683K->29196K(58368K)] [ParOldGen: 174953K->174589K(175104K)] 204637K->203785K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0180356 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-28T15:19:08.780-0800: 0.975: [Full GC (Ergonomics) [PSYoungGen: 29648K->29187K(58368K)] [ParOldGen: 174589K->174589K(175104K)] 204237K->203776K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0019913 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-28T15:19:08.782-0800: 0.977: [Full GC (Allocation Failure) [PSYoungGen: 29187K->29187K(58368K)] [ParOldGen: 174589K->174569K(175104K)] 203776K->203757K(233472K), [Metaspace: 2706K->2706K(1056768K)], 0.0126396 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
Heap
 PSYoungGen      total 58368K, used 29696K [0x00000007bab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 29696K, 100% used [0x00000007bab00000,0x00000007bc800000,0x00000007bc800000)
  from space 28672K, 0% used [0x00000007bc800000,0x00000007bc800000,0x00000007be400000)
  to   space 28672K, 0% used [0x00000007be400000,0x00000007be400000,0x00000007c0000000)
 ParOldGen       total 175104K, used 174569K [0x00000007b0000000, 0x00000007bab00000, 0x00000007bab00000)
  object space 175104K, 99% used [0x00000007b0000000,0x00000007baa7a708,0x00000007bab00000)
 Metaspace       used 2737K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 298K, capacity 386K, committed 512K, reserved 1048576K
```

频繁出现GC，且Full GC后续连续出现，最后一次Full GC的原因为Allocation Failure，导致OOM。从JVM进程退出时的情况来看Eden区使用了100%的内存，Old区使用到99%，无法回收，导致新对象无法被分配，形成OOM。



3、使用串行GC

```java
$ java -XX:+UseSerialGC -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

正在执行...
执行结束！共生成对象次数:8688
```

```java
Java HotSpot(TM) 64-Bit Server VM (25.211-b12) for bsd-amd64 JRE (1.8.0_211-b12), built on Apr  1 2019 20:53:18 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 8388608k(98692k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseSerialGC 
2020-10-28T15:28:38.624-0800: 0.208: [GC (Allocation Failure) 2020-10-28T15:28:38.624-0800: 0.208: [DefNew: 34728K->4352K(39296K), 0.0081914 secs] 34728K->11164K(126720K), 0.0083447 secs] [Times: user=0.00 sys=0.01, real=0.01 secs] 
2020-10-28T15:28:38.649-0800: 0.234: [GC (Allocation Failure) 2020-10-28T15:28:38.649-0800: 0.234: [DefNew: 39296K->4341K(39296K), 0.0096874 secs] 46108K->23457K(126720K), 0.0097583 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-10-28T15:28:38.666-0800: 0.250: [GC (Allocation Failure) 2020-10-28T15:28:38.666-0800: 0.250: [DefNew: 39285K->4347K(39296K), 0.0072680 secs] 58401K->35864K(126720K), 0.0073398 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:28:38.678-0800: 0.262: [GC (Allocation Failure) 2020-10-28T15:28:38.678-0800: 0.262: [DefNew: 39255K->4346K(39296K), 0.0048949 secs] 70771K->44710K(126720K), 0.0049692 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-10-28T15:28:38.687-0800: 0.271: [GC (Allocation Failure) 2020-10-28T15:28:38.687-0800: 0.271: [DefNew: 39131K->4349K(39296K), 0.0065496 secs] 79495K->56787K(126720K), 0.0066196 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-28T15:28:38.697-0800: 0.282: [GC (Allocation Failure) 2020-10-28T15:28:38.697-0800: 0.282: [DefNew: 39012K->4350K(39296K), 0.0061825 secs] 91451K->68574K(126720K), 0.0062471 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:28:38.708-0800: 0.292: [GC (Allocation Failure) 2020-10-28T15:28:38.708-0800: 0.292: [DefNew: 39091K->4351K(39296K), 0.0068876 secs] 103314K->81228K(126720K), 0.0069666 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-10-28T15:28:38.719-0800: 0.303: [GC (Allocation Failure) 2020-10-28T15:28:38.719-0800: 0.303: [DefNew: 39087K->4348K(39296K), 0.0090186 secs]2020-10-28T15:28:38.728-0800: 0.312: [Tenured: 91715K->91343K(91740K), 0.0114274 secs] 115964K->94237K(131036K), [Metaspace: 2706K->2706K(1056768K)], 0.0206696 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2020-10-28T15:28:38.755-0800: 0.340: [GC (Allocation Failure) 2020-10-28T15:28:38.755-0800: 0.340: [DefNew: 60891K->7616K(68544K), 0.0163141 secs] 152234K->117809K(220784K), 0.0164058 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2020-10-28T15:28:38.780-0800: 0.365: [GC (Allocation Failure) 2020-10-28T15:28:38.780-0800: 0.365: [DefNew: 68544K->7607K(68544K), 0.0158098 secs] 178737K->137530K(220784K), 0.0158947 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:28:38.804-0800: 0.389: [GC (Allocation Failure) 2020-10-28T15:28:38.804-0800: 0.389: [DefNew: 68444K->7614K(68544K), 0.0115642 secs] 198367K->156753K(220784K), 0.0116584 secs] [Times: user=0.00 sys=0.01, real=0.01 secs] 
2020-10-28T15:28:38.823-0800: 0.408: [GC (Allocation Failure) 2020-10-28T15:28:38.823-0800: 0.408: [DefNew: 68542K->7614K(68544K), 0.0116162 secs]2020-10-28T15:28:38.835-0800: 0.420: [Tenured: 167597K->154263K(167676K), 0.0207734 secs] 217681K->154263K(236220K), [Metaspace: 2706K->2706K(1056768K)], 0.0326745 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-10-28T15:28:38.882-0800: 0.467: [GC (Allocation Failure) 2020-10-28T15:28:38.882-0800: 0.467: [DefNew: 102904K->12799K(115776K), 0.0371176 secs] 257168K->192366K(372884K), 0.0372062 secs] [Times: user=0.01 sys=0.01, real=0.03 secs] 
2020-10-28T15:28:38.935-0800: 0.520: [GC (Allocation Failure) 2020-10-28T15:28:38.935-0800: 0.520: [DefNew: 115751K->12800K(115776K), 0.0631172 secs] 295317K->227037K(372884K), 0.0631966 secs] [Times: user=0.02 sys=0.01, real=0.06 secs] 
2020-10-28T15:28:39.013-0800: 0.598: [GC (Allocation Failure) 2020-10-28T15:28:39.013-0800: 0.598: [DefNew: 115751K->12797K(115776K), 0.0275332 secs] 329988K->266020K(372884K), 0.0276198 secs] [Times: user=0.02 sys=0.01, real=0.03 secs] 
2020-10-28T15:28:39.053-0800: 0.638: [GC (Allocation Failure) 2020-10-28T15:28:39.053-0800: 0.638: [DefNew: 115773K->12799K(115776K), 0.0224373 secs]2020-10-28T15:28:39.076-0800: 0.660: [Tenured: 289074K->237338K(289188K), 0.0342482 secs] 368996K->237338K(404964K), [Metaspace: 2706K->2706K(1056768K)], 0.0569986 secs] [Times: user=0.05 sys=0.01, real=0.06 secs] 
2020-10-28T15:28:39.140-0800: 0.725: [GC (Allocation Failure) 2020-10-28T15:28:39.140-0800: 0.725: [DefNew: 158272K->19776K(178048K), 0.0168849 secs] 395610K->291589K(573616K), 0.0169569 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-28T15:28:39.177-0800: 0.761: [GC (Allocation Failure) 2020-10-28T15:28:39.177-0800: 0.761: [DefNew: 178048K->19772K(178048K), 0.0373646 secs] 449861K->333150K(573616K), 0.0374676 secs] [Times: user=0.02 sys=0.02, real=0.04 secs] 
2020-10-28T15:28:39.235-0800: 0.819: [GC (Allocation Failure) 2020-10-28T15:28:39.235-0800: 0.819: [DefNew: 178044K->19775K(178048K), 0.0376976 secs] 491422K->382276K(573616K), 0.0377794 secs] [Times: user=0.01 sys=0.01, real=0.04 secs] 
2020-10-28T15:28:39.294-0800: 0.879: [GC (Allocation Failure) 2020-10-28T15:28:39.294-0800: 0.879: [DefNew: 178047K->19775K(178048K), 0.0313252 secs]2020-10-28T15:28:39.326-0800: 0.910: [Tenured: 411445K->291579K(411484K), 0.0451604 secs] 540548K->291579K(589532K), [Metaspace: 2706K->2706K(1056768K)], 0.0767576 secs] [Times: user=0.06 sys=0.01, real=0.08 secs] 
2020-10-28T15:28:39.392-0800: 0.977: [GC (Allocation Failure) 2020-10-28T15:28:39.392-0800: 0.977: [DefNew: 194496K->24254K(218752K), 0.0193864 secs] 486075K->352738K(704720K), 0.0194753 secs] [Times: user=0.01 sys=0.01, real=0.02 secs] 
2020-10-28T15:28:39.434-0800: 1.018: [GC (Allocation Failure) 2020-10-28T15:28:39.434-0800: 1.018: [DefNew: 218750K->24255K(218752K), 0.0209632 secs] 547234K->403149K(704720K), 0.0210415 secs] [Times: user=0.02 sys=0.01, real=0.02 secs] 
2020-10-28T15:28:39.477-0800: 1.061: [GC (Allocation Failure) 2020-10-28T15:28:39.477-0800: 1.061: [DefNew: 218751K->24255K(218752K), 0.0248345 secs] 597645K->463390K(704720K), 0.0250059 secs] [Times: user=0.02 sys=0.00, real=0.03 secs] 
2020-10-28T15:28:39.526-0800: 1.110: [GC (Allocation Failure) 2020-10-28T15:28:39.526-0800: 1.110: [DefNew: 218751K->24254K(218752K), 0.0450711 secs]2020-10-28T15:28:39.571-0800: 1.155: [Tenured: 504406K->357270K(504408K), 0.0462365 secs] 657886K->357270K(723160K), [Metaspace: 2706K->2706K(1056768K)], 0.0916516 secs] [Times: user=0.07 sys=0.02, real=0.09 secs] 
Heap
 def new generation   total 268032K, used 9686K [0x0000000740000000, 0x00000007522d0000, 0x000000076aaa0000)
  eden space 238272K,   4% used [0x0000000740000000, 0x00000007409758d0, 0x000000074e8b0000)
  from space 29760K,   0% used [0x000000074e8b0000, 0x000000074e8b0000, 0x00000007505c0000)
  to   space 29760K,   0% used [0x00000007505c0000, 0x00000007505c0000, 0x00000007522d0000)
 tenured generation   total 595452K, used 357270K [0x000000076aaa0000, 0x000000078f01f000, 0x00000007c0000000)
   the space 595452K,  59% used [0x000000076aaa0000, 0x0000000780785858, 0x0000000780785a00, 0x000000078f01f000)
 Metaspace       used 2712K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
```

简单来看，出现的GC更多。

4、CMS

```java
$ java -XX:+UseConcMarkSweepGC -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

正在执行...
执行结束！共生成对象次数:9662
```

```java
Java HotSpot(TM) 64-Bit Server VM (25.211-b12) for bsd-amd64 JRE (1.8.0_211-b12), built on Apr  1 2019 20:53:18 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 8388608k(511392k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:MaxNewSize=348966912 -XX:MaxTenuringThreshold=6 -XX:OldPLABSize=16 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:+UseParNewGC 
2020-10-28T15:45:36.181-0800: 0.126: [GC (Allocation Failure) 2020-10-28T15:45:36.181-0800: 0.126: [ParNew: 34920K->4336K(39296K), 0.0029573 secs] 34920K->7362K(126720K), 0.0030707 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-28T15:45:36.196-0800: 0.141: [GC (Allocation Failure) 2020-10-28T15:45:36.196-0800: 0.141: [ParNew: 39280K->4352K(39296K), 0.0064843 secs] 42306K->20723K(126720K), 0.0065703 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.208-0800: 0.153: [GC (Allocation Failure) 2020-10-28T15:45:36.208-0800: 0.153: [ParNew: 38960K->4343K(39296K), 0.0057442 secs] 55332K->30634K(126720K), 0.0058204 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.217-0800: 0.162: [GC (Allocation Failure) 2020-10-28T15:45:36.217-0800: 0.162: [ParNew: 39287K->4346K(39296K), 0.0070916 secs] 65578K->43143K(126720K), 0.0071654 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.229-0800: 0.174: [GC (Allocation Failure) 2020-10-28T15:45:36.229-0800: 0.174: [ParNew: 39290K->4346K(39296K), 0.0062156 secs] 78087K->54049K(126720K), 0.0062943 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.235-0800: 0.180: [GC (CMS Initial Mark) [1 CMS-initial-mark: 49702K(87424K)] 54487K(126720K), 0.0001271 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-28T15:45:36.236-0800: 0.181: [CMS-concurrent-mark-start]
2020-10-28T15:45:36.251-0800: 0.196: [CMS-concurrent-mark: 0.015/0.015 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2020-10-28T15:45:36.251-0800: 0.196: [GC (Allocation Failure) 2020-10-28T15:45:36.251-0800: 0.196: [ParNew: 39290K->4350K(39296K), 0.0071690 secs] 88993K->66091K(126720K), 0.0072333 secs] [Times: user=0.02 sys=0.01, real=0.00 secs] 
2020-10-28T15:45:36.259-0800: 0.204: [CMS-concurrent-preclean-start]
2020-10-28T15:45:36.259-0800: 0.204: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-28T15:45:36.259-0800: 0.204: [CMS-concurrent-abortable-preclean-start]
2020-10-28T15:45:36.263-0800: 0.208: [GC (Allocation Failure) 2020-10-28T15:45:36.263-0800: 0.208: [ParNew: 39236K->4343K(39296K), 0.0064337 secs] 100977K->77875K(126720K), 0.0065095 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.274-0800: 0.219: [GC (Allocation Failure) 2020-10-28T15:45:36.274-0800: 0.219: [ParNew: 39159K->4351K(39296K), 0.0057749 secs] 112692K->88011K(126720K), 0.0058460 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.283-0800: 0.228: [GC (Allocation Failure) 2020-10-28T15:45:36.283-0800: 0.228: [ParNew: 39175K->4348K(39296K), 0.0080185 secs] 122835K->103015K(138484K), 0.0080870 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.295-0800: 0.240: [GC (Allocation Failure) 2020-10-28T15:45:36.295-0800: 0.240: [ParNew: 39103K->4351K(39296K), 0.0072587 secs] 137771K->116166K(151720K), 0.0073305 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.306-0800: 0.251: [GC (Allocation Failure) 2020-10-28T15:45:36.306-0800: 0.251: [ParNew: 39295K->4351K(39296K), 0.0089755 secs] 151110K->132554K(168104K), 0.0090533 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.319-0800: 0.264: [GC (Allocation Failure) 2020-10-28T15:45:36.319-0800: 0.264: [ParNew: 38952K->4351K(39296K), 0.0064804 secs] 167156K->144148K(179740K), 0.0065578 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.330-0800: 0.275: [GC (Allocation Failure) 2020-10-28T15:45:36.330-0800: 0.275: [ParNew: 39277K->4330K(39296K), 0.0051055 secs] 179074K->153717K(189300K), 0.0051753 secs] [Times: user=0.02 sys=0.01, real=0.00 secs] 
2020-10-28T15:45:36.340-0800: 0.285: [GC (Allocation Failure) 2020-10-28T15:45:36.340-0800: 0.285: [ParNew: 39069K->4349K(39296K), 0.0062267 secs] 188455K->164972K(200564K), 0.0062899 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-10-28T15:45:36.350-0800: 0.295: [GC (Allocation Failure) 2020-10-28T15:45:36.350-0800: 0.295: [ParNew: 39293K->4351K(39296K), 0.0070049 secs] 199916K->178206K(213880K), 0.0070717 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-10-28T15:45:36.361-0800: 0.306: [GC (Allocation Failure) 2020-10-28T15:45:36.361-0800: 0.306: [ParNew: 39157K->4351K(39296K), 0.0072402 secs] 213012K->191294K(226944K), 0.0073063 secs] [Times: user=0.03 sys=0.01, real=0.00 secs] 
2020-10-28T15:45:36.372-0800: 0.317: [GC (Allocation Failure) 2020-10-28T15:45:36.372-0800: 0.317: [ParNew: 39295K->4350K(39296K), 0.0080588 secs] 226238K->204967K(240624K), 0.0081258 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.384-0800: 0.329: [GC (Allocation Failure) 2020-10-28T15:45:36.384-0800: 0.329: [ParNew: 38730K->4336K(39296K), 0.0078334 secs] 239346K->218274K(253960K), 0.0078986 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.396-0800: 0.341: [GC (Allocation Failure) 2020-10-28T15:45:36.396-0800: 0.341: [ParNew: 39040K->4345K(39296K), 0.0076478 secs] 252979K->231087K(266616K), 0.0077132 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.408-0800: 0.353: [GC (Allocation Failure) 2020-10-28T15:45:36.408-0800: 0.353: [ParNew: 39102K->4350K(39296K), 0.0070387 secs] 265845K->243089K(278732K), 0.0071080 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.419-0800: 0.364: [GC (Allocation Failure) 2020-10-28T15:45:36.419-0800: 0.364: [ParNew: 39294K->4349K(39296K), 0.0062518 secs] 278033K->253148K(288824K), 0.0063178 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.429-0800: 0.375: [GC (Allocation Failure) 2020-10-28T15:45:36.430-0800: 0.375: [ParNew: 39125K->4345K(39296K), 0.0058357 secs] 287924K->263516K(299176K), 0.0059022 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.439-0800: 0.384: [GC (Allocation Failure) 2020-10-28T15:45:36.439-0800: 0.384: [ParNew: 39237K->4349K(39296K), 0.0086334 secs] 298408K->278252K(313824K), 0.0086991 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.453-0800: 0.398: [GC (Allocation Failure) 2020-10-28T15:45:36.453-0800: 0.398: [ParNew: 39293K->4342K(39296K), 0.0058812 secs] 313196K->288590K(324212K), 0.0059446 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-10-28T15:45:36.462-0800: 0.407: [GC (Allocation Failure) 2020-10-28T15:45:36.462-0800: 0.407: [ParNew: 39078K->4349K(39296K), 0.0077293 secs] 323326K->301766K(337420K), 0.0077944 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.474-0800: 0.419: [GC (Allocation Failure) 2020-10-28T15:45:36.474-0800: 0.419: [ParNew: 39095K->4329K(39296K), 0.0077274 secs] 336512K->314753K(350340K), 0.0077917 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.485-0800: 0.430: [GC (Allocation Failure) 2020-10-28T15:45:36.486-0800: 0.431: [ParNew: 39273K->4351K(39296K), 0.0077456 secs] 349697K->328208K(363796K), 0.0078287 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.497-0800: 0.442: [GC (Allocation Failure) 2020-10-28T15:45:36.497-0800: 0.442: [ParNew: 39295K->4345K(39296K), 0.0068151 secs] 363152K->339529K(375108K), 0.0068832 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.508-0800: 0.453: [GC (Allocation Failure) 2020-10-28T15:45:36.508-0800: 0.453: [ParNew: 39289K->4350K(39296K), 0.0072779 secs] 374473K->352293K(387832K), 0.0073394 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.519-0800: 0.464: [GC (Allocation Failure) 2020-10-28T15:45:36.519-0800: 0.464: [ParNew: 38999K->4329K(39296K), 0.0062708 secs] 386941K->362186K(397856K), 0.0063329 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.529-0800: 0.474: [GC (Allocation Failure) 2020-10-28T15:45:36.529-0800: 0.474: [ParNew: 38964K->4348K(39296K), 0.0083097 secs] 396821K->376411K(412044K), 0.0083830 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.543-0800: 0.488: [GC (Allocation Failure) 2020-10-28T15:45:36.543-0800: 0.488: [ParNew: 39108K->4344K(39296K), 0.0087870 secs] 411171K->386450K(421988K), 0.0088605 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.565-0800: 0.510: [GC (Allocation Failure) 2020-10-28T15:45:36.566-0800: 0.511: [ParNew: 39054K->4349K(39296K), 0.0119770 secs] 421160K->401039K(436652K), 0.0120643 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.583-0800: 0.528: [GC (Allocation Failure) 2020-10-28T15:45:36.583-0800: 0.528: [ParNew: 39293K->4338K(39296K), 0.0100794 secs] 435983K->413792K(449384K), 0.0101580 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.598-0800: 0.543: [GC (Allocation Failure) 2020-10-28T15:45:36.598-0800: 0.543: [ParNew: 39282K->4346K(39296K), 0.0119385 secs] 448736K->428506K(464128K), 0.0120275 secs] [Times: user=0.03 sys=0.01, real=0.02 secs] 
2020-10-28T15:45:36.616-0800: 0.561: [GC (Allocation Failure) 2020-10-28T15:45:36.616-0800: 0.561: [ParNew: 39273K->4345K(39296K), 0.0067431 secs] 463433K->436815K(472420K), 0.0068186 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.628-0800: 0.573: [GC (Allocation Failure) 2020-10-28T15:45:36.628-0800: 0.573: [ParNew: 39150K->4333K(39296K), 0.0080458 secs] 471620K->445986K(481736K), 0.0081152 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.641-0800: 0.586: [GC (Allocation Failure) 2020-10-28T15:45:36.641-0800: 0.586: [ParNew: 38906K->4348K(39296K), 0.0084611 secs] 480559K->458007K(493708K), 0.0085397 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.654-0800: 0.599: [GC (Allocation Failure) 2020-10-28T15:45:36.654-0800: 0.599: [ParNew: 39024K->4351K(39296K), 0.0100979 secs] 492682K->469794K(505540K), 0.0101739 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.669-0800: 0.614: [GC (Allocation Failure) 2020-10-28T15:45:36.669-0800: 0.614: [ParNew: 39188K->4351K(39296K), 0.0097864 secs] 504631K->481555K(517216K), 0.0098462 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.684-0800: 0.629: [GC (Allocation Failure) 2020-10-28T15:45:36.684-0800: 0.629: [ParNew: 39251K->4348K(39296K), 0.0085224 secs] 516455K->494156K(529784K), 0.0086515 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.698-0800: 0.643: [GC (Allocation Failure) 2020-10-28T15:45:36.698-0800: 0.643: [ParNew: 39292K->4347K(39296K), 0.0083988 secs] 529100K->507332K(542980K), 0.0084917 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.712-0800: 0.657: [GC (Allocation Failure) 2020-10-28T15:45:36.712-0800: 0.657: [ParNew: 39056K->4346K(39296K), 0.0082641 secs] 542041K->520048K(555748K), 0.0083395 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.724-0800: 0.669: [GC (Allocation Failure) 2020-10-28T15:45:36.724-0800: 0.669: [ParNew: 39014K->4349K(39296K), 0.0083969 secs] 554716K->533528K(569296K), 0.0084939 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.737-0800: 0.682: [GC (Allocation Failure) 2020-10-28T15:45:36.737-0800: 0.682: [ParNew: 39034K->4342K(39296K), 0.0075524 secs] 568213K->545894K(581540K), 0.0076177 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.749-0800: 0.694: [GC (Allocation Failure) 2020-10-28T15:45:36.749-0800: 0.694: [ParNew: 39286K->4344K(39296K), 0.0078044 secs] 580838K->558808K(594444K), 0.0078743 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.761-0800: 0.706: [GC (Allocation Failure) 2020-10-28T15:45:36.761-0800: 0.706: [ParNew: 38943K->4346K(39296K), 0.0069390 secs] 593407K->570292K(606060K), 0.0070079 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-10-28T15:45:36.773-0800: 0.718: [GC (Allocation Failure) 2020-10-28T15:45:36.773-0800: 0.718: [ParNew: 39186K->4345K(39296K), 0.0069347 secs] 605132K->581770K(617460K), 0.0070178 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.784-0800: 0.729: [GC (Allocation Failure) 2020-10-28T15:45:36.784-0800: 0.729: [ParNew: 39289K->4350K(39296K), 0.0069735 secs] 616714K->593105K(628912K), 0.0070402 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.794-0800: 0.739: [GC (Allocation Failure) 2020-10-28T15:45:36.794-0800: 0.739: [ParNew: 39294K->4351K(39296K), 0.0088396 secs] 628049K->606605K(642312K), 0.0089036 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.808-0800: 0.753: [GC (Allocation Failure) 2020-10-28T15:45:36.808-0800: 0.753: [ParNew: 39121K->4347K(39296K), 0.0072230 secs] 641374K->617293K(653048K), 0.0072943 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.819-0800: 0.764: [GC (Allocation Failure) 2020-10-28T15:45:36.819-0800: 0.764: [ParNew: 39162K->4351K(39296K), 0.0095541 secs] 652108K->632395K(668152K), 0.0096220 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.832-0800: 0.777: [GC (Allocation Failure) 2020-10-28T15:45:36.832-0800: 0.777: [ParNew: 38956K->4351K(39296K), 0.0093733 secs] 667000K->645269K(681108K), 0.0094384 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.847-0800: 0.792: [GC (Allocation Failure) 2020-10-28T15:45:36.847-0800: 0.792: [ParNew: 39196K->4338K(39296K), 0.0114097 secs] 680114K->658148K(694004K), 0.0114965 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.863-0800: 0.808: [GC (Allocation Failure) 2020-10-28T15:45:36.863-0800: 0.808: [ParNew: 38657K->4347K(39296K), 0.0097047 secs] 692467K->670427K(706296K), 0.0097754 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.877-0800: 0.822: [GC (Allocation Failure) 2020-10-28T15:45:36.877-0800: 0.822: [ParNew: 39253K->4350K(39296K), 0.0106371 secs] 705334K->682606K(718332K), 0.0107063 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.892-0800: 0.837: [GC (Allocation Failure) 2020-10-28T15:45:36.892-0800: 0.837: [ParNew: 39203K->4346K(39296K), 0.0115973 secs] 717460K->694416K(730244K), 0.0116580 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.910-0800: 0.855: [GC (Allocation Failure) 2020-10-28T15:45:36.910-0800: 0.855: [ParNew: 39290K->4343K(39296K), 0.0085693 secs] 729360K->704228K(740120K), 0.0086320 secs] [Times: user=0.02 sys=0.01, real=0.00 secs] 
2020-10-28T15:45:36.923-0800: 0.868: [GC (Allocation Failure) 2020-10-28T15:45:36.923-0800: 0.868: [ParNew: 39093K->4350K(39296K), 0.0110074 secs] 738978K->716791K(752676K), 0.0110896 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.939-0800: 0.884: [GC (Allocation Failure) 2020-10-28T15:45:36.939-0800: 0.884: [ParNew: 39294K->4351K(39296K), 0.0109965 secs] 751735K->729779K(765508K), 0.0111421 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-10-28T15:45:36.955-0800: 0.900: [GC (Allocation Failure) 2020-10-28T15:45:36.955-0800: 0.900: [ParNew: 39092K->4347K(39296K), 0.0121781 secs] 764520K->743095K(778888K), 0.0122618 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:36.973-0800: 0.918: [GC (Allocation Failure) 2020-10-28T15:45:36.973-0800: 0.918: [ParNew: 39234K->4340K(39296K), 0.0126431 secs] 777982K->757135K(792952K), 0.0127106 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:36.990-0800: 0.935: [GC (Allocation Failure) 2020-10-28T15:45:36.990-0800: 0.935: [ParNew: 39284K->4343K(39296K), 0.0102501 secs] 792079K->768176K(803916K), 0.0103288 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:37.006-0800: 0.951: [GC (Allocation Failure) 2020-10-28T15:45:37.006-0800: 0.951: [ParNew: 39267K->4349K(39296K), 0.0124628 secs] 803100K->779917K(815792K), 0.0125405 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:37.024-0800: 0.969: [GC (Allocation Failure) 2020-10-28T15:45:37.024-0800: 0.969: [ParNew: 39226K->4351K(39296K), 0.0103299 secs] 814794K->791171K(827044K), 0.0104098 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:37.038-0800: 0.983: [GC (Allocation Failure) 2020-10-28T15:45:37.038-0800: 0.983: [ParNew: 39295K->4336K(39296K), 0.0085281 secs] 826115K->802475K(838280K), 0.0085959 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:37.051-0800: 0.996: [GC (Allocation Failure) 2020-10-28T15:45:37.051-0800: 0.996: [ParNew: 39280K->4351K(39296K), 0.0075980 secs] 837419K->812217K(848136K), 0.0076599 secs] [Times: user=0.03 sys=0.01, real=0.00 secs] 
2020-10-28T15:45:37.063-0800: 1.008: [GC (Allocation Failure) 2020-10-28T15:45:37.063-0800: 1.008: [ParNew: 39031K->4340K(39296K), 0.0105981 secs] 846896K->822710K(858600K), 0.0106650 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:37.079-0800: 1.024: [GC (Allocation Failure) 2020-10-28T15:45:37.079-0800: 1.024: [ParNew: 38710K->4346K(39296K), 0.0091396 secs] 857080K->833530K(869332K), 0.0092089 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:37.092-0800: 1.037: [GC (Allocation Failure) 2020-10-28T15:45:37.092-0800: 1.037: [ParNew: 39290K->4345K(39296K), 0.0075596 secs] 868474K->843228K(879124K), 0.0076266 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:37.104-0800: 1.049: [GC (Allocation Failure) 2020-10-28T15:45:37.104-0800: 1.049: [ParNew: 38911K->4350K(39296K), 0.0103829 secs] 877794K->855358K(891268K), 0.0104580 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-28T15:45:37.119-0800: 1.064: [GC (Allocation Failure) 2020-10-28T15:45:37.119-0800: 1.064: [ParNew: 38886K->4351K(39296K), 0.0094515 secs] 889893K->868083K(903956K), 0.0095192 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-28T15:45:37.132-0800: 1.077: [GC (Allocation Failure) 2020-10-28T15:45:37.132-0800: 1.077: [ParNew: 39295K->4344K(39296K), 0.0118312 secs] 903027K->881918K(917816K), 0.0118966 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
Heap
 par new generation   total 39296K, used 23211K [0x0000000740000000, 0x0000000742aa0000, 0x0000000754cc0000)
  eden space 34944K,  53% used [0x0000000740000000, 0x000000074126cc00, 0x0000000742220000)
  from space 4352K,  99% used [0x0000000742660000, 0x0000000742a9e090, 0x0000000742aa0000)
  to   space 4352K,   0% used [0x0000000742220000, 0x0000000742220000, 0x0000000742660000)
 concurrent mark-sweep generation total 878520K, used 877574K [0x0000000754cc0000, 0x000000078a6ae000, 0x00000007c0000000)
 Metaspace       used 2712K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
```

- -XX:MaxNewSize=348966912：Young区最大为348966912（333mb）
- -XX:MaxTenuringThreshold=6 ：对象晋升老年代的阙值（经历6次Young GC存活的对象）
- -XX:OldPLABSize=16 ：每个块初始化数量。Old区采用CMS，空闲内存时碎片的
- -XX:+UseConcMarkSweepGC：Old采用CMS收回
-  -XX:+UseParNewGC ：Young区采用ParNew收回

5、G1

```java
$ java -XX:+UseG1GC -Xloggc:gc.demo.log -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis

正在执行...
执行结束！共生成对象次数:5164
```

```java
Java HotSpot(TM) 64-Bit Server VM (25.211-b12) for bsd-amd64 JRE (1.8.0_211-b12), built on Apr  1 2019 20:53:18 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 8388608k(179424k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC 
2020-10-28T16:09:47.979-0800: 0.234: [GC pause (G1 Evacuation Pause) (young) 15M->4798K(128M), 0.0024013 secs]
2020-10-28T16:09:47.989-0800: 0.244: [GC pause (G1 Evacuation Pause) (young) 20M->10142K(128M), 0.0030265 secs]
2020-10-28T16:09:48.005-0800: 0.260: [GC pause (G1 Evacuation Pause) (young) 41M->18M(128M), 0.0034873 secs]
2020-10-28T16:09:48.047-0800: 0.302: [GC pause (G1 Evacuation Pause) (young) 111M->50M(152M), 0.0112771 secs]
2020-10-28T16:09:48.067-0800: 0.322: [GC pause (G1 Evacuation Pause) (young) 94M->64M(152M), 0.0040722 secs]
2020-10-28T16:09:48.072-0800: 0.327: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 72M->67M(152M), 0.0030269 secs]
2020-10-28T16:09:48.075-0800: 0.330: [GC concurrent-root-region-scan-start]
2020-10-28T16:09:48.075-0800: 0.330: [GC concurrent-root-region-scan-end, 0.0000784 secs]
2020-10-28T16:09:48.075-0800: 0.330: [GC concurrent-mark-start]
2020-10-28T16:09:48.077-0800: 0.332: [GC concurrent-mark-end, 0.0011570 secs]
2020-10-28T16:09:48.077-0800: 0.332: [GC remark, 0.0014475 secs]
2020-10-28T16:09:48.078-0800: 0.333: [GC cleanup 73M->73M(152M), 0.0002615 secs]
2020-10-28T16:09:48.087-0800: 0.342: [GC pause (G1 Evacuation Pause) (young) 120M->86M(152M), 0.0030440 secs]
2020-10-28T16:09:48.090-0800: 0.345: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 87M->87M(152M), 0.0019347 secs]
2020-10-28T16:09:48.092-0800: 0.347: [GC concurrent-root-region-scan-start]
2020-10-28T16:09:48.092-0800: 0.347: [GC concurrent-root-region-scan-end, 0.0000640 secs]
2020-10-28T16:09:48.092-0800: 0.347: [GC concurrent-mark-start]
2020-10-28T16:09:48.094-0800: 0.349: [GC concurrent-mark-end, 0.0020224 secs]
2020-10-28T16:09:48.095-0800: 0.350: [GC remark, 0.0016004 secs]
2020-10-28T16:09:48.096-0800: 0.351: [GC cleanup 92M->92M(152M), 0.0003411 secs]
2020-10-28T16:09:48.102-0800: 0.357: [GC pause (G1 Evacuation Pause) (young) 124M->99M(306M), 0.0043840 secs]
2020-10-28T16:09:48.146-0800: 0.401: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 194M->127M(612M), 0.0096982 secs]
2020-10-28T16:09:48.156-0800: 0.411: [GC concurrent-root-region-scan-start]
2020-10-28T16:09:48.156-0800: 0.411: [GC concurrent-root-region-scan-end, 0.0001326 secs]
2020-10-28T16:09:48.156-0800: 0.411: [GC concurrent-mark-start]
2020-10-28T16:09:48.157-0800: 0.412: [GC concurrent-mark-end, 0.0008603 secs]
2020-10-28T16:09:48.157-0800: 0.412: [GC remark, 0.0008463 secs]
2020-10-28T16:09:48.158-0800: 0.413: [GC cleanup 130M->130M(612M), 0.0004473 secs]
2020-10-28T16:09:48.437-0800: 0.692: [GC pause (G1 Evacuation Pause) (young) 514M->230M(952M), 0.0523201 secs]
2020-10-28T16:09:48.656-0800: 0.911: [GC pause (G1 Evacuation Pause) (young) 467M->290M(1172M), 0.0420271 secs]
2020-10-28T16:09:48.882-0800: 1.137: [GC pause (G1 Evacuation Pause) (young) 619M->370M(1348M), 0.0153850 secs]
```

G1每次GC的时间和延迟相对来说都非常短。





##### 使用压测工具(wrk或sb)，演练gateway-server-0.0.1-SNAPSHOT.jar 示例

1、串行

```java
java -jar -Xms1g -Xmx1g -XX:+UseSerialGC gateway-server-0.0.1-SNAPSHOT.jar 
```

```
$ wrk -t8 -c40 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.74ms   29.96ms 334.59ms   94.73%
    Req/Sec     4.35k     1.09k    7.35k    84.98%
  2029612 requests in 1.00m, 242.32MB read
Requests/sec:  33774.35
Transfer/sec:      4.03MB
```

2、并行

```java
java -jar -Xms1g -Xmx1g -XX:+UseParallelGC gateway-server-0.0.1-SNAPSHOT.jar 
```

```
g$ wrk -t8 -c40 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     9.29ms   36.54ms 466.35ms   94.50%
    Req/Sec     4.22k     1.08k    7.11k    85.84%
  1952295 requests in 1.00m, 233.09MB read
Requests/sec:  32507.39
Transfer/sec:      3.88MB
```

3、CMS

```java
 java -jar -Xms1g -Xmx1g -XX:+UseConcMarkSweepGC gateway-server-0.0.1-SNAPSHOT.jar 
```

```
g$ wrk -t8 -c40 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.48ms   25.33ms 313.09ms   93.53%
    Req/Sec     3.96k     1.13k    9.70k    83.01%
  1859801 requests in 1.00m, 222.04MB read
Requests/sec:  30951.05
Transfer/sec:      3.70MB
```

4、G1

```java
java -jar -Xms1g -Xmx1g -XX:+UseG1GC gateway-server-0.0.1-SNAPSHOT.jar 
```

```
$ wrk -t8 -c40 -d60s http://localhost:8088/api/hello
Running 1m test @ http://localhost:8088/api/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     9.85ms   38.86ms 447.19ms   94.72%
    Req/Sec     3.69k     1.07k    9.81k    80.22%
  1711459 requests in 1.00m, 204.33MB read
Requests/sec:  28480.19
Transfer/sec:      3.40MB
```



