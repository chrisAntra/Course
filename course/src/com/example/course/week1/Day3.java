//package com.example.course.week1;
//
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class Day3 {
//    static int recursion(int n){
//        if(n==1) return 1;
//        return recursion(n-1)+n;
//    }
//    public static void main(String[] args) {
//        System.out.println(recursion(5));
//        System.out.println(Thread.currentThread().getName());
//    }
//}
///**
// *  JVM
// *      heap+ stack
// *      main thread stack [main recursion(5) rescurion(4)...rescurison(1) ]
// *                  stack
// *      List<Integer> list =new LinkedList();
// *      heap
// *          [eden ][ survivor0][ survivor1] young generation   (parallelNewGC/parallelGC) STW
// *          [            old              ] old generation      (parallelOldGC/ConcurrentMarkSweep)
// *          [                             ] perm generation
// *                                           metaspace
// *          new LinkedList()
// *          new Object()-> eden(after gc) -> promote to s1/s0 -> promote to old
// *      STW
// *      GC root : Thread /classloader
// *      minor GC :parallelNewGC/parallelGC
// *      major GC : in old generation
// *      full GC : both in young + old
// *      After
// *      G1:
// *       [][S][][][][][][][][]
// *       [][][][E][][][][][][]
// *       [][][][][][][O][][][]
// *       [][][][][][][][][][]
// *       https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html
// *
// */
///**
// *  Thread
// *      1.extend a thread class
// *      2.provide a runnable
// *
// *      Thread lifecycle
// *          1. create thread -> new
// *          2. start -> active
// *          3. sleep/wait/lock -> wait/ block
// *          4. terminated
// *
// */
//class TestThread extends Thread{
////    @Override
////    public void run(){
////        System.out.println(Thread.currentThread().getName()+"says: IM A NEW THREAD");
////    }
//
//    public static void main(String[] args) throws Exception{
//        System.out.println(Thread.currentThread().getName());
////        TestThread testThread = new TestThread();
////        testThread.start();
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName()+ " Another thread!");
//            }
//        });
//        t1.start();
//        t1.join();
//    }
//}
///**
// * Threadsafe
// *           lock/ synchronized
// *           Reetrantlock
// *           lock.lock()
// *           ...
// *           lock.unclock();
// *
// */
//class Test{
//    public static void main(String[] args) {
//    }
//}
///**
// * volatile
// *      1.visibility
// *      2. happen-before
// *      3. prevent reordering
// *
// */
//class VisibilityTest{
//    private static volatile Boolean flag = false;
//
//    public static void main(String[] args)throws Exception {
//        Thread t1= new Thread(()->{
//          while(!flag){
//              if(flag){
//                  break;
//              }
//          }
//            System.out.println(Thread.currentThread().getName()+"says: Im out!");
//        });
//        Thread t2 = new Thread(()->{
//            try{
//                Thread.sleep(3000);
//            }catch (Exception ex){
//                System.out.println(ex);
//            }
//            flag = true;
//        });
//
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println("Im finished");
//    }
//}
///**
// * race condition
// *  thread1 thread2
// *   i=1     i=1
// *   i=2     i=2
// *   want is i=3;
// */
//class RunCust implements Runnable{
//    @Override
//    public void run(){
//        System.out.println("wo annoy");
//    }
//}
//class TestRaceCondition{
//    static  int i;
//    //this
//    public  synchronized  static void add(){
//
//            for (int j = 0; j < 10000; j++) {
//                i++;
//            }
//
//    }
//
//    public static void main(String[] args) throws Exception{
////        TestRaceCondition tc = new TestRaceCondition();
////        TestRaceCondition tc2 = new TestRaceCondition();
//        //
//        Thread t1 = new Thread(()->{add();});
//
//        Thread t2 = new Thread(()->{add();});
//        Thread t3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//
//        RunCust r1 = new RunCust();
////        Thread t3 = new Thread(r1);
//        t1.start();
//        t2.start();
//        t3.start();
//        t3.join();
//        t1.join();
//        t2.join();
//        System.out.println(i);
//    }
//}
/**
 *  synchronized (monitor)
 *      1.threadsafe /visibility
 *      2. create a critical section
 *      3. no fair lock
 *       t2 t3 t4
 *      4. synchronized only for object
 *          synchronized non-static block (monitor of "this")
 *          sychronized(object)
 *          sychronized static block (class)
 *
 *      Reentrantlock Condition
 *          empty
 *          full
 */

/**
 * Implement a threadsafe queue
 */
//class MyQueue{
//    Queue<Integer> que = new LinkedList<>();
//    public synchronized void add(Integer num){
//          while(size==capactity){
//            this.wait();
//          }
//        this.que.offer(num);
//        notifyAll(); // wake up the waiting thread;
//    }
//    public synchronized Integer poll()throws Exception{
//        while(que.isEmpty()){
//           this.wait();// let thread to release the monitor
////            Thread.sleep(100);
//        }
//        return que.poll();
//    }
//}
//class TestMyQueue{
//    public static void main(String[] args)throws Exception {
//        MyQueue mq = new MyQueue();
//        Thread t1 = new Thread(()->{
//            try{
//                Thread.sleep(5000);
//            }catch (Exception ex){}
//           mq.add(100);
//        });
//        Thread t2 = new Thread(()->{
//            try{
//            System.out.println(mq.poll());
//            }catch(Exception ex){}
//        });
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//    }
//}