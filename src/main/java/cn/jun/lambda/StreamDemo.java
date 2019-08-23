package cn.jun.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args){
//        List<Person> list = new ArrayList<>();
//        list.add(new Person("张三",23));
//        list.add(new Person("王五",14));
//        list.add(new Person("李四",39));
//        list.add(new Person("james",43));
//        list.add(new Person("age",43));
//        List<String> result = list.stream()
//                .filter(x -> x.getAge()==43)
//                .map(Person::getName)
//                .collect(Collectors.toList());
//        result.forEach(re -> {
//            System.out.println(re);
//        });
//        list.indexOf(new Person("",1));
//        Map<String,String> s = new HashMap<String,String>();
        Person p1 = new Person("张三",23);
        Person p2 = new Person("张三",23);
        System.out.println(p1.hashCode() +":"+ p2.hashCode());
        System.out.println(p1.equals(p2));
        List<Person> list = new LinkedList<>();
        list.add(p1);
        list.add(p2);
        System.out.println(list.indexOf(p2));
    }

}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

//    @Override
//    public int hashCode() {
//
//        return Objects.hash(name, age);
//    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
