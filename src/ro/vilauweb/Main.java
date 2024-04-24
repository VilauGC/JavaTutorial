package ro.vilauweb;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        /**
         * 1. Predicate
         *      a.	How to implement with anonymous class, lambda expression, and method reference
         *      b.	What convenience default methods it has
         *      c.	Where is it used in the standard java library
         */

        // Anonymous class
        Predicate<String> startsWithM = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("M");
            }
        };

        // Lambda
        Predicate<String> startsWithMLambda = (String s) -> s.startsWith("M");

        // Method Reference
        Predicate<String> isEmtpyMethodReference = String::isEmpty;
        System.out.println("Using Anonymous class: should print true: " + startsWithM.test("Messi"));
        System.out.println("Using Lambda Expression: should print true: " + startsWithMLambda.test("Messi"));
        System.out.println("Using Method Reference: should print true: " + isEmtpyMethodReference.test(""));

        /**
         * Lets see what convenience methods does Predicate has:
         */

        /**
         * Method and(Predicate other) has the following description:
         * Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
         * So basically we need another Predicate
         */
        Predicate<String> lengthBiggerThan5 = s -> s.length() > 5;
        System.out.println("Does Messi Lionel starts with M and has length bigger than 5? "
                + startsWithM.and(lengthBiggerThan5).test("Messi Lionel"));

        /**
         * Method isEqual(Object targetRef)
         * Returns a predicate that tests if two arguments are equal according to Objects.equals(Object, Object).
         */

        System.out.println(Predicate.isEqual("Messi").test("Messi"));

        /**
         * Method negate()
         * Returns a predicate that represents the logical negation of this predicate.
         */

        Predicate<String> doesntStartWithM = startsWithM.negate();
        System.out.println("Should return true: " + doesntStartWithM.test("Cristiano Ronaldo"));

        /**
         * Method or(Predicate other)
         * Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.
         */

        Predicate<String> startsWithC = s -> s.startsWith("C");
        System.out.println("Should return true if starts with M or C: " + startsWithM.or(startsWithC).test("Cristiano Ronaldo"));

        /**
         * Where is it used:
         * List => ArrayList => removeIf(Predicate filter)
         */

        List<String> list = new ArrayList<>();
        list.add("Cristiano");
        list.add("David Beckham");
        list.add("Lionel Messi");
        list.add("Carlitos Tevez");

        System.out.println("Before removing all the players starting with C: " + list);
        list.removeIf((String player) -> player.startsWith("C"));
        System.out.println("After removing all the players starting with C: " + list);

        /**
         * Stream interface
         * allMatch(Predicate predicate)
         * anyMatch(Predicate predicate)
         * filter(Predicate predicate)
         * noneMatch(Predicate predicate)
         */

        List<String> players = List.of("Cristiano", "Camavinga", "Canavaro", "Cocu");
        // allMatch()
        System.out.println("Should return true if all the players in the collection starts with C: " + players.stream().allMatch(startsWithC));

        Predicate<String> isMessi = s -> s.equals("Messi");

        // noneMatch()
        System.out.println("Should return true if it doesn't contain Messi: " + players.stream().noneMatch(isMessi));

        List<String> newListWithMessi = new ArrayList<>(players);
        newListWithMessi.add("Messi");

        // anyMatch()
        System.out.println("Should return true if one is Messi: " + newListWithMessi.stream().anyMatch(isMessi));

        // filter()
        System.out.println("Should return the new list without Messi: " + newListWithMessi.stream().filter(isMessi.negate()).collect(Collectors.toList()));

        /**
         * Using a CustomPredicate
         */

        MyCustomPredicate<String, Long> myCustomPredicate = (String s, Long l) -> s.length() > l;
        System.out.println("Is Lionel Messi bigger than 5? " + myCustomPredicate.test("Lionel Messi", 5L));
    }
}
