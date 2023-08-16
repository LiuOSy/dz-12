package org.example;

public class Woman extends Person {

    private String originalLastName = null;


    public Woman(String firstName, String lastName, int age) throws Exception {
        super(firstName, lastName, age);
    }

    public Woman(String firstName, String lastName, int age, Person partner) throws Exception {
        super(firstName, lastName, age, partner);
    }

    public String getOriginalLastName() {
        return originalLastName;
    }

    @Override
    public boolean isRetired() {
        return this.getAge() >= 60;
    }

    @Override
    protected void marriagePostProcessing(Person spouse) throws Exception {
        originalLastName = getLastName();
        setLastName(spouse.getLastName());
    }

    @Override
    protected void divorcePostProcessing(Person spouse) throws Exception {
        setLastName(originalLastName);
    }

}
