package org.example;

import org.example.exception.*;

abstract public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Person spouse = null;

    public Person(String firstName, String lastName, int age) throws Exception {
        setFirstName(firstName);
        setLastName(lastName);
        validateAge(age);
        this.age = age;
    }

    public Person(String firstName, String lastName, int age, Person spouse) throws Exception {
         this(firstName, lastName, age);
         this.spouse = spouse;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName == null)
            throw new NullFirstNameException();
        this.firstName = firstName;
    }

    public String getLastName() {
         return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (lastName == null)
                throw new NullLastNameException();
        this.lastName = lastName;
    }

    public Person getSpouse() {
         return spouse;
    }

    private void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        validateAge(age);
        this.age = age;
    }

    public boolean isMarried() {
        return spouse != null;
    }

    abstract public boolean isRetired();

    public static void registerMarriage(Person spouse1, Person spouse2) throws Exception {
        if (spouse1 == null || spouse2 == null)
            throw new NullSpouseException("Marriage cannot be processed! Less than two people are given.");
        if (spouse1.isMarried() || spouse2.isMarried())
            throw new MarriageCannotBeRegisteredInvalidStatusException("Marriage cannot be registered! One or both people are already married!");
        if (spouse1.getAge() < 18 || spouse2.getAge() < 18)
            throw new MarriageCannotBeRegisteredAgeException("Marriage cannot be registered! One or both partners are too young!");
        if (spouse1 == spouse2)
            throw new MarriageCannotBeRegisteredSelfMarriageException("Marriage cannot be registered! Self marriage is not allowed!");
        if (spouse1.getClass() == spouse2.getClass())
            throw new MarriageCannotBeRegisteredGenderException("Marriage cannot be registered! Partners have the same gender!");

        spouse1.setSpouse(spouse2);
        spouse2.setSpouse(spouse1);
        spouse1.marriagePostProcessing(spouse2);
        spouse2.marriagePostProcessing(spouse1);

    }

    abstract protected void marriagePostProcessing(Person spouse) throws Exception;

    public static void registerDivorce(Person spouse1, Person spouse2, boolean returnOriginalLastName) throws Exception {
        if (spouse1 == null || spouse2 == null)
            throw new NullSpouseException("Divorce cannot be processed! Less than two people are given.");
        if (spouse1.getSpouse() != spouse2 || spouse2.getSpouse() != spouse1)
            throw new DivorceCannotBeProcessedException("Divorce cannot be processed!");
        spouse1.setSpouse(null);
        spouse2.setSpouse(null);
        if (returnOriginalLastName) {
            spouse1.divorcePostProcessing(spouse2);
            spouse2.divorcePostProcessing(spouse1);
        }
    }
    
    abstract protected void divorcePostProcessing(Person spouse) throws Exception;

    private void validateAge(int age) throws Exception {
        if (age < 0 || age > 150)
            throw new IncorrectAgeValueException(String.format("Age should be between 0 and 150. Age entered is %d", age));
    }

    @Override
    public String toString() {
        String marriageStatus = isMarried() ?
                String.format("Spouse is %s %s. ", spouse.getFirstName(), spouse.getLastName()) :
                "Not married. ";

        return String.format("%s %s, %d years. %s", getFirstName(), getLastName(), getAge(), marriageStatus);
    }



}
