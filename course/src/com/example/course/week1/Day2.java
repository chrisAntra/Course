import java.util.HashMap;

//package com.example.course.week1;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.*;
//
//public class Day2 {
//    public static void main(String[] args) {}
//}
///**
// *          Throwable
// *          /           \
// *          Error       Exception
// *
// *          uncheck                                      checked
// *          NullPoint                                   IOException
// *          ArrayIndexOutOfBounds                       FileNotFound
// *          IllegalArgument                             ClassNotFoundEx
// */
//class TestException{
//    public static void main(String[] args) {
//        try{
//            FileReader file = new FileReader("e:/wrong");
//            BufferedReader buffRead = new BufferedReader(file);
//            while(buffRead.ready()){
//                System.out.println(buffRead.readLine());
//            }
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//
//    }
//}
///**
// *  resolve exception
// *      1. use throws
// *      2. use try/catch/finally
// *
// */
///**
// * 1.Array
// *      fix size
// *
// */
//class TestArray{
//    public static void main(String[] args) {
//        Object[] arr1 = new Integer[10];
//        System.out.println(arr1[0].equals(1));
//    }
//}
///**
// * List/Map/Set
// */
//class MyList<T> {
//    static private class Node<T>{
//        Node<T> pre;
//        Node<T> next;
//        T val;
//        public Node(){}
//        public Node(T val){
//            this.val = val;
//        }
//    }
//    private Node head;
//    private Node tail;
//    private int capacity;
//    public MyList(){
//        head = new Node();
//        tail = new Node();
//        head.next=tail;
//        tail.pre= head;
//        // head-> tail->null
//    }
//    public int size() {return this.capacity;}
//    public void add(T val){
//        Node newNode = new Node(val);
//        tail.pre.next =newNode;
//        newNode.pre= tail.pre;
//        newNode.next=tail;
//        tail.pre=newNode;
//        this.capacity++;
//    }
//    public T get(int index){
//        if(index>capacity-1||index<0) return null;//
//        Node<T> res=head;
//        for(int i=0;i<index+1;i++){
//            res=res.next;
//        }
//        return res.val;
//
//    }
//}
//class Test{
//    public static void main(String[] args) {
//        MyList<Integer> myList = new MyList<>();
//        myList.add(1);
//        myList.add(2);
//        myList.add(3);
//        System.out.println(myList.get(0));
//        System.out.println(myList.get(1));
//        System.out.println(myList.get(2));
//    }
//}
///**
// * Diff LinkedList vs ArrayList
// *
// *         ArrayList:
// *              1. array
// *              2. access time O(1)
// *              3. resize O(N)
// *              4 insert/delete 0(N)
// *              5. continuous space
//*         LinkedList:
// *              1.node+ pointer
// *              2. random space
// *              3. insert delete for first and last will be O(1)
// *              4. access O(N)
// *              5. remove-> find O(N) + O(1)
// */
//
///**
// *  Iterator
// *      for each
// *      list(modCount++)
// *      iterator ExceptedModCount
// */
//class TestIterator{
//    public static void main(String[] args) {
//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        System.out.println("Before: "+list);
////        for(Integer num:list){
////            list.remove(new Integer(3));
////            //exception
////        }
//        Iterator<Integer> it = list.iterator();
//        // hasNext() next() remove()
//        System.out.println(it);
//        while(it.hasNext()){
////            int temp;
//            System.out.println(it.next());
////            if(temp==3) it.remove();
//        }
//        System.out.println("After: "+list);
//    }
//}
///**
// *  HashMap
// *      key equals  hashcode need to same
// *      hashcode() equals()
// *      [][][][][][][][][]
// *      Node[]
// *      hash % n
// *  flow
// *      1. putVal(hash(key), key, value,....)
// *      2. get index -> i = (n - 1) & hash
// *      3 if table[index] ==null put NewNode(key-value);
// *          if node is treenode we will method for the tree to put key value;
// *          if linedlist , we just loop throught linkedlist and use equals()
// *      4. after insertion check whether exceed the limit ,if yes resize
// *
// */
//class Student{
//    int id;
//    String name;
//
//    public Student(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Student)) return false;
//        Student student = (Student) o;
//        return id == student.id && Objects.equals(name, student.name);
//    }
//
//    @Override
//    public int hashCode() {
////        return Objects.hash(id, Name);
//        int res =17;
//        res= res*31+this.id;
//        if(this.name!=null) res=res*31+this.name.hashCode();
//        return res;
//    }
//}
//class TestHM{
//    public static void main(String[] args) {
//        Hashtable<Student, Integer> ht = new Hashtable<>();
//        //[]
//        //[][][] concurrenthashmap
//        ht.put(null,1);
////        HashMap<Student, Integer> hm = new HashMap<>();
////        Student stu1= new Student(1, "xx1");
////        Student stu2= new Student(1,"xx1");
////        System.out.println("Whether whey are equals: "+stu1.equals(stu2));
////        hm.put(stu1,10);
////        System.out.println(hm.containsKey(stu2));
//    }
//}
///**
// * HashSet
// *         implement by HashMap
// */
//class TestSet{
//    public static void main(String[] args) {
//        HashSet<Integer> hs = new HashSet<>();
//    }
//}
///**
// * TreeMap
// * redBlack tree
// * Ctrl+B for source code
// */
//class TestTreeMap{
//    public static void main(String[] args) {
//        TreeMap<Integer,Integer> tm = new TreeMap<>((a,b)->{
//            if(a==null) return 0; //dummy logic just for tree be able to insert null value as key;
//            if(b==null) return 0;
//            return b-a;});
//        tm.put(1,1);
//        tm.put(5,5);
//        tm.put(2,4);
////        tm.put(null,5);
//        System.out.println(tm);
//        // 2
// //       1 5
//    }
//}
