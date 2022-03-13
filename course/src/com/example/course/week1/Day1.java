//package com.example.course.week1;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class Day1 {
//    public static void main(String[] args) {
//        List<Integer>  list  = new LinkedList<>();
//    }
//}
///*
//1. JAVA OOP
//    everything is an object
//    OOP
//        Polymorphism:
//            method loading
//            method overriding
//            upcast
//
//        Inheritance:
//
//        Encapsulation:
//            private
//        Abstraction:
//            Interface
//                public final static
//                java 8 : static default method
//                java 9 : private
//            abstract class
// */
//class Person{
//    public static int id;
//    private String name;
////    public Person(){}
////    public Person(){};
//    static{
//        id=1;
//
//    }
//
//    public Person(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public  void display(){
//        System.out.println("This is a Person!");
//    }
//    public void display(String s){
//        System.out.println("This is method overloading:"+ s);
//    }
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
//
//
//class Student extends Person{
//    static int s=1;
//    private int grade;
//    static {
//        System.out.println("This is a static block");
//    }
//    {
//        System.out.println("This is a non-static block");
//    }
//    public Student(){
//        super( 1,"NAME");
//        //
//        this.id =10;
//        grade =100;
//
//    }
//    public Student(int id, String name){
//        super(id,name);
//    }
//    public static void dummy(){
//
//    }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "name=" + this.getName() +
//                '}';
//    }
//
//    @Override
//    public void display(){
//        System.out.println(super.id+", "+grade);
//        System.out.println("This is a Student!");
//    }
//    public void displayStu(){
//        System.out.println("This only for Studnet!");
//    }
//
//}
//class Test{
//    public static void main(String[] args) {
//        Student stu1 = new Student();
//        stu1.displayStu();
//        stu1.display();
//        Student.s=2;
//
//        Student stu2 = new Student();
////        LinkedList<Integer> lst = new LinkedList<>();
////        lst.addFirst(2);
////        System.out.println(lst);
//        System.out.println(Student.s);
//    }
//}
///*
//2. Naming Convention
//    class: Leading with Uppercase MyClass
//    package: com edu...
//    method: toString
//    variable leading lower case
//    constant: Integer.MAX_VALUE
//
// */
//
///*
//3. Static
//    method
//    variable
//
// */
//
///*
//4. java is pass by value
//    reference variable
// */
////class Test1{
////    static void changeName(Student stu){
////
////        stu.setName("another name");
////    }
////    static void changeStu(Student stu){
////        stu = new Student(10,"XXX");
////        System.out.println(stu);
////    }
////    public static void main(String[] args) {
////        int a=1;
////        Student stu1  = new Student();
////        Student stu2 = stu1;
////        System.out.println(stu1);
////        changeStu(stu1);
////        System.out.println(stu1);
////    }
////}
//class Test1{
//    static void changeNum(int j){
//        j=10;
//    }
//    public static void main(String[] args) {
//        int i=1;
//        System.out.println("i: "+i);
//        changeNum(i);
//
//        System.out.println("i after modifying: "+ i);
//    }
//}
//class Test2{
//    final void method1(){}
//    final void method1(String s){}
//}
///*
//5. final
//    class: cannot be inherited
//    method: cannot be overriding
//    variable: once initiated, you cannot change the value;
//
//
//6. Immutable
//       String
// */
//final class ImmutableList<T>{
//    private final List<T> list;
//    public ImmutableList(List<T> list){
//        //deepCopy
////        this.list= Libaray.deepCopy(list);
//        this.list = new LinkedList<>(list);
//        //this is not a deepCopy list of list
//        //list of list Inter
//    }
//    public List<T> getList(){
//        //deepCopy
//        return new LinkedList<>(this.list);
//    }
//}
// /*
// 6. exceptions
//  */
///*
//7. finally
// */
