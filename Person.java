abstract class Person {
    //Abstract class Person
    private String name;
    private int passportNumber;

    //Constructor for the person class (super constructor) as its an abstract class
    public Person(String name, int passportNumber) {
        //Sets up the passportNumber and name variables in the constructor as they are private.
        this.passportNumber = passportNumber;
        this.name = name;
    }

    //Abstract method used to calculate the weight of a person
    public abstract double calculatePersonWeight();

    //Getter for name
    public String getName() {
        return name;
    }

    //Setter for name
    public void setName(String name) {
        this.name = name;
    }

    //Getter for passportNumber
    public int getPassportNumber() {
        return passportNumber;
    }

    //Setter for passportNumber
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
}