package com.example.course.week1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Day4 {
    public static void main(String[] args) {}
}
/**
 *  drawback of synchronized
 *      1. performance slow
 *      2. one waiting list
 *      3. no fair
 *      4. no try lock
 */
/**
 *  volatile + CAS / AtomicLibrary
 *  increment number in threadsafe env
 *  CAS compare and set -> atomic
 *      read i=1    read i=1
 *      write i=2   write i=2
 *      expect , new value
 *  compareAndSet(Object, offset, expect, newVal)
 *                 testAto  ai      i       i+1
 */
class TestAtomic{
    static AtomicInteger ai = new AtomicInteger(0);
    public static void add(){
        ai.incrementAndGet();
    }

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(()->{
            for(int i=0;i<10000;i++){
                add();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<10000;i++){
                add();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(ai);

    }
}
/**
 *  class MyLock{
 *      private volatile boolean flag = false;
 *      private Thread currHolder;
 *      public boolean tryLock(){
 *      //    Unsafe unsafe = Unsafe.getUnsafe();
 *          if(unsafe.compareAndSet(this,flag,expected =false, true)) {
 *              currHolder = Thread.currenThread();
 *              flag = true;
 *              return true;
 *          } else {
 *              return false;
 *          }
 *      }
 *  }
 */
/**
 * ReentrantLock
 *
 *      condition producer
 *      condition consumer
 *
 *      Blocking queue
 *      producer
 *      T1                         T4
 *      T2 -> [][][][]...[][][][] ->   T5
 *      T3              50            T6
 *      200 prod  150 ( pro wl)
 *      after 1 min 200 con
 *               150(cos wl)
 *        150 com 150 prod
 */

/**
 *
 * t1 <- t2 <- t3
 */
class MyQueue<T>{
    private final Queue<T> queue;
    private int size=0;
    private int capactity=16;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition full =lock.newCondition();
    private final Condition empty = lock.newCondition();
    public MyQueue(){
        queue = new LinkedList<>();
    }
    public boolean offer(T e){
        lock.lock();
//        lock.tryLock();
//        ...
        try{
            while (size== capactity){
                full.await();
            }
            System.out.println("Adding"+e);
            queue.offer(e);
            size++;
            empty.signal();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            lock.unlock();
        }
        return true;
    }
    public T poll(){
        lock.lock();
        T e= null;
        try{
            while(size==0) {
                empty.await();
            }
            e = queue.poll();
            size--;
            full.signal();
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            lock.unlock();
        }
        return e;
    }
}

class Test{
    public static void main(String[] args) throws Exception{
        MyQueue<Integer> mq = new MyQueue<>();
        Thread p1 = new Thread(()->{mq.offer(1);});
        Thread p2 = new Thread(()->{

            mq.offer(2);});
        Thread c1 = new Thread(()->{
            System.out.println(mq.poll());
        });
        Thread c2 = new Thread(()->{
            System.out.println(mq.poll());
        });
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        p1.join();
        p2.join();
        c1.join();
        c2.join();
    }
}
/**
 * DeadLock
 *      t1 holding o1  try to get o2
 *      t2 holding o2  try to get o1
 *      o1   o2
 *      t1:
 *      synchronized(o1){
 *          ...
 *          synchronized(o2){
 *
 *          }
 *      }
 *      t2:
 *      synchronized(o2){
 *          ...
 *          synchronized(o1){
 *
 *          }
 *      }
 *
 */
/**
 * How to solve deadlock?
 *  1. release resource after certain time
 *  2. lock in order
 *  3. look up table
 *
 */

/**
 * threadPool
 *      1. reuse the thread
 *                                     T1
 *      [][][][][][][][3][2][1]        T2
 *                                     T3
 *       type
 *          CacheThreadPool (corePoolSize, Maximum, alive time, time unit)
 *                           3 core thread   creat temp
 *          FixedThreadPool
 *          SingleThreadPool
 *          scheduledTreadPool
 *          ForkjoinPool
 *           task -> multiple subtask -> use multiple thread -> join
 *       pow(1,2)++....+pow(10,2)           t1
 *                            pow(2,2)      t2
 *
 */
/**
 *  Runnable vs Callable
 *  void         return val
 *                  can throw exception
 */
/**
 * Executors, ExecutorService,Executor
 */

class TestExecutor{
    public static void main(String[] args) {
        ExecutorService anotherName = Executors.newSingleThreadExecutor();

        anotherName.submit(()->{
            System.out.println("My name is cat");
        });
        ExecutorService es1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
}
