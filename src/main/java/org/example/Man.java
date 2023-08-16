package org.example;

public class Man extends Person {
    public Man(String firstName, String lastName, int age) throws Exception {
        super(firstName, lastName, age);
    }

    public Man(String firstName, String lastName, int age, Person spouse) throws Exception {
        super(firstName, lastName, age, spouse);
    }

    @Override
    public boolean isRetired() {
        return this.getAge() >= 65;
    }

    @Override
    protected void marriagePostProcessing(Person spouse) {
        //No action required
    }

    @Override
    protected void divorcePostProcessing(Person spouse) {
        //No action required
    }

}
