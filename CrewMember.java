public class CrewMember extends Person {
    //Concrete class CrewMember which inherits from the abstract class Person

    //Constructor for the CrewMember class
    public CrewMember(String name, int passportNumber) {
        //Calls the super constructor located in Person
        super(name, passportNumber);
    }

    //toString method for the CrewMember class which returns the crew members name (Overrides the default toString method)
    @Override
    public String toString() {
        return getName();
    }

    //Inherited method from Person class
    public double calculatePersonWeight() {
        //Returns the number 75 as a double as every crew member has the same weight estimation
        double crewWeight;
        crewWeight = 75;
        return(crewWeight);
    }
}
