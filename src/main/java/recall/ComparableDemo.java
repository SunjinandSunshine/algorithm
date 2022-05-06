package recall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author SunnyJ
 * @Date 2022/5/6 19:32
 */
public class ComparableDemo {
    public static void main(String[] args) {
        Person p1 = new Person(1,6,"in");
        Person p2 = new Person(1,4,"sj");
        List<Person> list = new ArrayList<>();
        list.add(p1);list.add(p2);
        Collections.sort(list);
        list.forEach(p-> System.out.println(p.getName()));
    }
   static class Person implements Comparable<Person>{
       private int id;
       private int age;
       private String name;

       public Person(int id, int age, String name) {
           this.id = id;
           this.age = age;
           this.name = name;
       }

       public int getId() {
           return id;
       }

       public void setId(int id) {
           this.id = id;
       }

       public int getAge() {
           return age;
       }

       public void setAge(int age) {
           this.age = age;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }


       @Override
       public int compareTo(Person p) {
           return this.getAge() - p.getAge();
       }
   }
}
