package com.sabel.beispielMaven;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andreasmaier on 20.01.17.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        if (id != person.id) return false;
        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Person");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Person("Maier", 40));
        em.getTransaction().commit();
        Query query = em.createQuery("SELECT p from Person p");
        List<Person> personList = query.getResultList();
        for (Person person : personList) {
            System.out.println(person);
        }
        //Person person = em.find(Person.class, 1);
        //System.out.println("person = " + person);
        em.close();
        emf.close();

    }
}
