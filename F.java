import java.util.*;
public class F {

    public static void main(String... args) {
        System.out.println("There is a new way of doing the same this");

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("robus", 34));
        persons.add(new Person("Rahul", 22));
        persons.add(new Person("Nutt", 1));


        //print all the names in the persone

        // for (Person p: persons) {
        //     System.out.println(p.getName());
        // }


        //here is the for loop that prints out cerain condition

        List<Person> drinkers = Lists.filter(persons, new Predicate<Boolean, Person>(){
            public Boolean apply(Person p) {
                return p.getAge() >= 22;
            }
        });

        for(Person p: drinkers) {
            System.out.println(p.getName());
        }

        //mapping predicate simply takes the persons collections and add the surname to each returning the new List
        List<Person> addSurnames = Lists.map(persons, new MappingPredicate<Person, Person>() {
            public Person apply(Person p) {
                return new Person(p.getName() + " surname", p.getAge());
            }
        });
        //mapping the persons to strings

        List<String> names = Lists.map(persons, new MappingPredicate<Person, String>() {
            public String apply(Person p) {
                return p.getName();
            }
        });
        //reducing the all the persons into the total sum

        Integer totalAge = Lists.reduce(0, persons, new ReducePredicate<Integer, Person>(){
            public Integer apply(Integer acc, Person person) {
                return acc + person.getAge();
            } 
        });

        String insane = Lists.reduce("<<", persons, new ReducePredicate<String, Person>() {
            public String apply(String acc, Person person) {
                return acc + "( " + person.getName() +" )";
            }
        });

        System.out.println("Total age: " + String.valueOf(totalAge));
        System.out.println("String" + insane);

        for(Person p: addSurnames) {
            System.out.println(p.getName());
        }

        for(String name: names) {
            System.out.println(name);
        }

    }
}

interface Predicate<B, T1> {
    public B apply(T1 t);
}

interface MappingPredicate<T1, T2> {
    //takes in type of T1 and return type of T2
    //person to string or watervare
    public T2 apply(T1 t);
}

interface ReducePredicate<A,  T> {
    public A apply(A a, T t);
}
class Lists {
    public static <T> List<T> filter(List<T> src, Predicate<Boolean, T> predicate) {
        List<T> results = new ArrayList<T>();
        for(T t: src) {
            if(predicate.apply(t)) {
                results.add(t);
            }
        }
        return results;
    } 

    public static <T1, T2> List<T2> map(List<T1> src, MappingPredicate<T1, T2> predicate) {
        List<T2> results = new ArrayList<T2>();
        for(T1 t: src) {
            T2 n = predicate.apply(t);
            results.add(n);
        }
        return results;
    }
    //accumulator A
    public static <A, T> A reduce(A seed, List<T> src, ReducePredicate<A, T> reduce) {
        A accum = seed;
        for(T t: src) {
            accum  = reduce.apply(accum, t);

        }
        return accum;
    } 
}


class Person {
    private String name;
    private Integer age;
    
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;

    }


    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}