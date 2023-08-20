package org.example;

import org.example.exception.IncorrectAgeValueException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class PersonDataProviderTest {

    private enum ExpectedStatus {
        TRUE,
        FALSE,
        EXCEPTION
    }

    @DataProvider(name = "manRetirementStatusTestData")
    public Object[][] generateManRetirementTestData() {
        return new Object[][]{
                {ExpectedStatus.FALSE,      "Jerry",    "Peters",       0},
                {ExpectedStatus.FALSE,      "John",     "Smith",        15},
                {ExpectedStatus.FALSE,      "Sam",      "Johnson",      59},
                {ExpectedStatus.FALSE,      "Robert",   "Scott",        60},
                {ExpectedStatus.FALSE,      "Daniel",   "Daniels",      61},
                {ExpectedStatus.FALSE,      "Peter",    "Johnson",      64},
                {ExpectedStatus.TRUE,       "Tony",     "Scott",        65},
                {ExpectedStatus.TRUE,       "Jack",     "Daniels",      66},
                {ExpectedStatus.TRUE,       "Steve",    "Doe",          100},
                {ExpectedStatus.TRUE,       "Tom",      "Rogers",       150},
                {ExpectedStatus.EXCEPTION,  "Adam",     "Thompson ",    151},
                {ExpectedStatus.EXCEPTION,  "Sam",      "Adams",        152},
                {ExpectedStatus.EXCEPTION,  "Ken",      "Robson",       -1},
                {ExpectedStatus.EXCEPTION,  "Bob",      "Jackson",      -23}
        };
    }

    @DataProvider(name = "womanRetirementStatusTestData")
    public Object[][] generateWomanRetirementTestData() {
        return new Object[][]{
                {ExpectedStatus.FALSE,  "Mary",     "Smith",    0},
                {ExpectedStatus.FALSE,  "Mary",     "Black",    15},
                {ExpectedStatus.FALSE,  "Mary",     "Johnson",  59},
                {ExpectedStatus.TRUE,   "Mary",     "Scott",    60},
                {ExpectedStatus.TRUE,   "Mary",     "Daniels",  61},
                {ExpectedStatus.TRUE,   "Mary",     "White",    64},
                {ExpectedStatus.TRUE,   "Mary",     "Peters",   65},
                {ExpectedStatus.TRUE,   "Mary",     "Roberts",  66},
                {ExpectedStatus.TRUE,   "Mary",     "Doe",      100},
                {ExpectedStatus.TRUE,   "Mary",     "Rogers",   150}
        };
    }

    @DataProvider(name = "womanIncorrectAgeTestData")
    public Object[][] generateWomanIncorrectAgeTestData() {
        return new Object[][] {
                {ExpectedStatus.EXCEPTION, "Mary", "Thompson ", 151},
                {ExpectedStatus.EXCEPTION, "Mary", "Adams",     152},
                {ExpectedStatus.EXCEPTION, "Mary", "Robson",    -1},
                {ExpectedStatus.EXCEPTION, "Mary", "Jackson",   -23}
        };
    }

    @Test(dataProvider = "manRetirementStatusTestData")
    public void manRetirementStatusTest (ExpectedStatus status, String firstName, String lastName, int age) throws Exception {
        if (status == ExpectedStatus.TRUE) {
            Person man = new Man(firstName, lastName, age);
            Assert.assertTrue(man.isRetired());
        } else if (status == ExpectedStatus.FALSE) {
            Person man = new Man(firstName, lastName, age);
            Assert.assertFalse(man.isRetired());
        } else if (status == ExpectedStatus.EXCEPTION) {
            Assert.assertThrows(IncorrectAgeValueException.class, () -> {
                Person man = new Man(firstName, lastName, age);
            });
        }
    }

    @Test(dataProvider = "womanRetirementStatusTestData")
    public void womanRetirementStatusTest (ExpectedStatus status, String firstName, String lastName, int age) throws Exception {
        if (status == ExpectedStatus.TRUE) {
            Person woman = new Woman(firstName, lastName, age);
            Assert.assertTrue(woman.isRetired());
        } else if (status == ExpectedStatus.FALSE) {
            Person woman = new Woman(firstName, lastName, age);
            Assert.assertFalse(woman.isRetired());
        }
    }

    @Test(dataProvider = "womanIncorrectAgeTestData", expectedExceptions = IncorrectAgeValueException.class)
    public void womanIncorrectAgeExceptionTest (ExpectedStatus status, String firstName, String lastName, int age) throws Exception {
        if (status == ExpectedStatus.EXCEPTION) {
            Person woman = new Woman(firstName, lastName, age);
        }
    }

}
