package thread.practice1;

import java.util.Random;

public class _1多线程数组求和 {
    public static int sum1 = 0;
    public static int sum2 = 0;
    public static int sum = 0;
    static boolean  fished1 = false;
    static boolean  fished2 = false;
    public static void main(String[] args) {
//        给定一个很长的数组 (长度 1000w), 通过随机数的方式生成 1-100 之间的整数.
//        实现代码, 能够创建两个线程, 对这个数组的所有元素求和.
//        其中线程1 计算偶数下标元素的和, 线程2 计算奇数下标元素的和.
//        最终再汇总两个和, 进行相加
//        记录程序的执行时间.
        final int[] arr = new int[1000_0000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100) + 1;
        }

        Thread thread1 = new Thread(()->{
            for (int i = 1; i < 100; i += 2) {
                sum1 += arr[i];
            }
            fished1 = true;
        });
        Thread thread2 = new Thread(()->{
            for (int i = 2; i < 100; i += 2) {
                sum2 += arr[i];
            }
            fished2 = true;
        });
        long st =  System.currentTimeMillis();
        thread1.start();
        thread2.start();
        while (!fished1 || !fished2) {
        }
        System.out.println("数组的和为:" + sum1 + sum2);
        System.out.println("执行时间：" + (System.currentTimeMillis() - st) + "ms");
    }
}
