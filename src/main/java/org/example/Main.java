package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        // Valid scenario
        Person man1 = new Man("Jonh", "Adams", 95);
        Person woman1 = new Woman("Joanha", "Smith", 25);
        System.out.println("Initial");
        System.out.println(man1);
        System.out.println(woman1);

        Person.registerMarriage(woman1, man1);
        System.out.println("After marriage");
        System.out.println(man1);
        System.out.println(woman1);

        Person.registerDivorce(man1, woman1, true);
        System.out.println("After divorce");
        System.out.println(man1);
        System.out.println(woman1);

        Person man2 = new Man("Tom", "Cat", 19);
        // Exception due to Marriage cannot be registered!
        // Person.registerMarriage(man1, man2);

    }
}