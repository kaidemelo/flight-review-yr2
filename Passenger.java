public class Passenger extends Person {
    //Concrete class Passenger which inherits from the abstract class Person
    private int holdBags;
    private String flightClass;

    //Constructor for the Passenger class
    public Passenger(String name, int passportNumber, String flightClass, int holdBags) {
        //Calls the super constructor located in Person
        super(name, passportNumber);
        //Sets up the flightClass and holdBags variables in the constructor as they are private.
        this.flightClass = flightClass;
        this.holdBags = holdBags;
    }

    //Inherited method from Person class
    public double calculatePersonWeight() {
        //Determines whether the passenger is first class or economy and sets the variable personWeight to the appropriate weight
        double personWeight;
        if (getFlightClass().equals("first")) {
            personWeight = 87.5;
        } else {
            personWeight = 80;
        }
        //It then adds the persons weight to the total weight of bags the passenger has and returns the total as a double
        return(personWeight + (getHoldBags() * 25));
    }

    //Getter for holdBags
    public int getHoldBags() {
        return holdBags;
    }

    //Setter for holdBags
    public void setHoldBags(int holdBags) {
        this.holdBags = holdBags;
    }

    //Getter for flightClass
    public String getFlightClass() {
        return flightClass;
    }

    //Setter for flightClass
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
}
