package com.example.course.week2;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Day5 {
}
/**
 * Functional interface -> contains 1 abstract method
 *      1. runnable void run();
 *      2. callable V call() throws Ex
 *      3. comparator int compare(T t1, T t2)
 *      4. consumer void accept(T t)
 *      5. function R apply(T t)
 *      6. supplier T get()
 *      7. predicate: boolean test(T t)
 *      ...
 */
//class Test{
//    public static void main(String[] args) throws Exception {
//        Callable<Integer> c = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return 1;
//            }
//        };
//        System.out.println(c.call());
//    }
//}
//class Test{
//    public static void main(String[] args) {
//        Supplier<List<Integer>> sp = new Supplier<List<Integer>>() {
//            @Override
//            public List<Integer> get() {

//                return new LinkedList<>();
//            }
//        };
//        List<Integer> ls = sp.get();
//        ls.add(1);
//        System.out.println(ls);
//    }
//}
//@FunctionalInterface
//interface myFunc{
//    void myFunc();
//}

/**
 * lambda expressions
 */
class Test{
    public static void main(String[] args) {
        Supplier<List<Integer>> sp = ()->{
            System.out.println("Im here");
            List<Integer> list = new LinkedList<>();
            list.add(1);
            list.add(2);
            return list;};
        System.out.println(sp.get());
    }
}

/**
 * Stream API
 *     1.process the sequence of element ex. list of integer
 *     2. func1().func2().func3()
 *     stream api
 *          map
 *          mapToInt...
 *          filter
 *          collect
 *          forEach
 *          reduce
 */
class TestStream{
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,3,5,6,7,8,9);
        //multiply by 2
//        for(int i=0;i<nums.size();i++){
//            nums.set(i,nums.get(i)*2);
//        }
//        System.out.println(nums);

        List<Integer> res = nums.stream().map((x)->2*x).collect(Collectors.toList());
//        System.out.println(nums == res);
        System.out.println("Using stream: "+res);
        //convert int[] to List<Integer>
        double[] arr = {1,3,4,5,6,7};
        //int -> Integer
//        List<Double> toList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<Double> toList = Arrays.stream(arr).boxed().collect(()->{return new LinkedList<>();},  (re,val)->{re.add(val);},(l1,l2)->{});
        System.out.println("customized way: "+ toList);
        //Integer -> int
        int[] toArr = nums.stream().mapToInt((x)->(int)x).toArray();
        System.out.println(toArr);
        Arrays.stream(toArr).forEach(x-> System.out.println(x));

    }
}
class TestStream1{
    //counting the frequnecy of nums
    public static void main(String[] args) {
        List<Integer> lst = Arrays.asList(1,1,1,1,2,2,2,4,3,6,6,6,8,9,8);
//        Map<Integer,Integer> hm = lst.stream().collect(()-> new HashMap<Integer,Integer>(),(res,val)->{
//            res.put(val,res.getOrDefault(val,0)+1);
//        },(m1,m2)->{});
//        System.out.println(hm);
        Map<Integer,Long> hm1 = lst.stream().collect(Collectors.groupingBy((x)->x,Collectors.counting()));
        System.out.println(hm1);
    }
}
class Student{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
class TestFilter{
    public static  List<Student> filterByAge(List<Student> stus) throws Exception{
//        if(stus==null){
//            throw new Exception();
//        }
//        Optional<List<Student>> op = Optional.ofNullable(stus);
//        op.isPresent();
        return Optional.ofNullable(stus).orElse(new ArrayList<>()).stream().filter(stu->stu.getAge()>30).collect(Collectors.toList());
    }
}
/**
 * Optional
 *  is a wrapper class for you to handle the null check
 *  .of()
 *  .ofNullable()
 *  .get()
 *  .orElse(...)
 *  .
 */

/**
 * parallel stream
 *                           [1,3]
 * [1,3,4,5,6] ->forJoinPool [4,5] ->(*2)[2,6,8,10,12]->filter(>8) [10,12]
 *                            [6]
 */
class TestParallel{
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        System.out.println(list.parallelStream().map(x->{
            System.out.println("Step 1");
            return 2*x;
        }).filter((x)->{
            System.out.println("Step 2");
            return x>8;
        }).collect(()->{return new LinkedList<>();},  (re,val)->{re.add(val);},(l1,l2)->{l1.addAll(l2);}));

    }
}


/**
 * CompletableFuture
 */
// main thread
class TestFuture{
    public static void main(String[] args) throws Exception{

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<Integer> f = pool.submit(()->{
            System.out.println("Im here");

            return 10;
        });
        Integer a= f.get();
        //doing
        //....

    }
}

class TestCompletable{
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{

            try{
                Thread.sleep(3000);
            }catch (Exception e){}
            System.out.println("Step 1");
            return 10;
        }).thenApply((x)->{
            System.out.println("Step 1.1");
            return x-5;
        });

        System.out.println("other logic in main");
        CompletableFuture<Void> cf1 =cf.thenAccept(x->{
            System.out.println("Step 2: "+2*x);
        });
        System.out.println("Antoher logic in main");
        cf1.join();
        CompletableFuture.allOf();



    }
}
