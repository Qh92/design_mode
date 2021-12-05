/**
 * @author Qh
 * @version 1.0
 * @date 2021-12-03 21:58
 */
public class Person {

    int id;
    String name;
    int age;
    double weight;
    int score;
    Location loc;

    private Person() {}

    public static class PersonBuilder {
        Person person = new Person();
        public PersonBuilder basicInfo(int id,String name,int age) {
            person.id = id;
            person.name = name;
            person.age = age;
            return this;
        }

        public PersonBuilder weight(double weight) {
            person.weight = weight;
            return this;
        }
        public PersonBuilder score(int score) {
            person.score = score;
            return this;
        }
        public PersonBuilder loc(String street, String roomNo) {
            person.loc = new Location(street, roomNo);
            return this;
        }
        public Person build() {return person;}
    }
}

class Location {
    String street;
    String roomNo;

    public Location(String street, String roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }
}