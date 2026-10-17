package edu.mirea.pr41.person;

public class TestPerson {
    public static void main(String[] args) {
        Person[] people = {new Person(), new Person("Андреев Всеволод", 20)};
        for (Person person : people) {
            person.move();
            person.talk();
        }
    }
}
