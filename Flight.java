//Imports the array-list, scanner, and file-not-found java library's
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight {
    //Flight class
    private int flightNumber;
    private Aircraft craft;
    private String startLocation;
    private String endLocation;
    private double distance;

    //Declares two array lists, one for the flights seats, and the other for the crew members on this flight
    public ArrayList<Seat> seats;
    public ArrayList<CrewMember> crew;
    
    //Constructor for the Flight class
    public Flight(int flightNumber, Aircraft craft, String startLocation, String endLocation, double distance) {
        //Sets up the flight variables in the constructor as they are private
        this.flightNumber = flightNumber;
        this.craft = craft;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;

        //Initialises the array lists
        this.seats = new ArrayList();
        this.crew = new ArrayList();

        //Try catch block to catch the exception and return an error message if the the layout file for the flight is not found
        try {
            //Sets the crafts layout file for the flight as the input for the scanner object
            Scanner scan = new Scanner(craft.getLayoutFile());
            int row = 0;
            //While the layout file has another line, put the line of input into a string called thisLine
            while (scan.hasNextLine()) {
                String thisLine = scan.nextLine();
                row++;
                //This puts the input stored in thisLine into a string array currentRow, separated by commas using the .split method
                String[] currentRow = thisLine.split(",");
                //Then adds each seats class to the seat array list from the input retrieved from the layout file
                for (int i = 0; i < currentRow.length; i++) {
                    Seat seat = new Seat(row,i + 1, currentRow[i]);
                    seats.add(seat);
                }
            }
            //Closes scanner to avoid memory leak
            scan.close();
        } catch (FileNotFoundException e) {
            //Returns error message as the exception is caught
            System.out.println("Layout File Not Found For Flight: #" + flightNumber);
        }
    }

    public double calculateTakeOffWeight() {
        //Method to calculate the total weight of the flight using the crew, the crafts, and the passengers weights along with their bags
        double totalWeight = 0;

        //Calculates the weights of all the passengers using an enhanced for loop
        for (Seat seat : seats) {
            //New passenger from the allocated seat variable
            Passenger passenger = seat.getAllocatedTo();
            //If passenger is null then calculate the weight for the passenger and store in weight
            if (passenger != null) {
                double weight = passenger.calculatePersonWeight();
                //Increments the weight stored in the weight variable to the total weight variable
                totalWeight += weight;
            }
        }

        //Calculates the weights of all the crew members using an enhanced for loop
        for (CrewMember crewMember : crew) {
            //Calculates weight of crew member using the method in the CrewMember class
            double weight = crewMember.calculatePersonWeight();
            //Increments the weight stored in the weight variable to the total weight variable
            totalWeight += weight;
        }

        //Gets the weight of the craft and increments it to the total weight variable
        totalWeight += craft.getCraftWeight();

        //Logic for determining whether the total calculated weight of this flight is larger than the maximum take-off weight for the craft
        if (totalWeight <= craft.getMaximumTakeOffWeight()) {
            //Total weight the for flight is less than the maximum take off weight for the craft
            return totalWeight;
        } else {
            //Total weight for the flight is above the maximum take-off weight for the craft
            return -1;
        }
    }

    public int bookSeat(Passenger passenger) {
        //Method to book/upgrade/downgrade the passengers or to alert that the plane is full
        //Uses the freeSeats method i created to fill two variables with the count of free seats on a class of each flyingClass
        int freeFirstClassSeatsCount = freeSeats("F");
        int freeEconomyClassSeatsCount = freeSeats("E");
        //Sets flyingClass variable to the passed in passengers flightClass using a short form if else statement
        //This statement means if the logic in the brackets is true then set flyingClass to "F", else set it to "E"
        String flyingClass = (passenger.getFlightClass().equals("first")) ? "F" : "E";

        //Loops through all the seats in the seats array list using an enhanced for loop
        for (Seat seat : seats) {
            if (
            //Condition 1: Seat has not been allocated, and the passengers flightClass matches the seats flyingClass
                    seat.getAllocatedTo() == null &&
                    flyingClass.equals(seat.getFlyingClass())
            ) {
                //Allocate that seat to the passenger passed into the bookSeat method (Booking Completed)
                seat.setAllocatedTo(passenger);
                return 1;
            } else if (
            //Condition 2: The passengers flightClass is first but there are no available first class seats on the flight,
            // and the seats flyingClass is economy, and the Seat has not been allocated.
                    flyingClass.equals("F") &&
                    freeFirstClassSeatsCount == 0 &&
                    seat.getFlyingClass().equals("E") &&
                    seat.getAllocatedTo() == null
            ) {
                //Allocate that seat to the passenger passed into the bookSeat method (Passenger Downgraded)
                seat.setAllocatedTo(passenger);
                return 3;
            } else if (
            //Condition 3: The passengers flightClass is economy but there are no available economy class seats on the flight,
            // and the seats flyingClass is first, and the Seat has not been allocated.
                    flyingClass.equals("E") &&
                    freeEconomyClassSeatsCount == 0 &&
                    seat.getFlyingClass().equals("F") &&
                    seat.getAllocatedTo() == null
            ) {
                //Allocate that seat to the passenger passed into the bookSeat method (Passenger Upgraded)
                seat.setAllocatedTo(passenger);
                return 2;
            }
        }
        //Condition 4: Plane full as none of the other conditions were met (Plane Full)
        return -1;
    }

    private int freeSeats(String flyingClass) {
        //A method i created to check how many free seats are on a flight of a particular flyingClass
        int count = 0;
        //Calculates the how many free seats there are of a flyingClass using an enhanced for loop
        for (Seat seat : seats) {
            //Checks that the seat matches the flyingClass passed into the method and that the seat has not been allocated yet.
            if (flyingClass.equals(seat.getFlyingClass()) && seat.getAllocatedTo() == null) {
                //Increments the count variable
                count ++;
            }
        }
        //Returns the total count of free seats matching the flyingClass passed into the method
        return count;
    }

    private int totalFreeSeats() {
        //A method i created to check the total of free seats on a flight after booking regardless of which flyingClass the seat is
        int total = 0;
        //Gets free seats for first class
        total += freeSeats("F");
        //Gets free seats for economy class
        total += freeSeats("E");
        return total;
    }

    private int ClassTotalSeats(String flyingClass) {
        //A method i created to check how many booked seats are on a flight of a particular flyingClass
        int classCount = 0;
        for (Seat seat : seats) {
            //Checks that the seat matches the flyingClass passed into the method and that the seat has been allocated.
            if (flyingClass.equals(seat.getFlyingClass()) && seat.getAllocatedTo() != null) {
                //Increments the classCount variable
                classCount ++;
            }
        }
        //Returns the total count of booked seats matching the flyingClass passed into the method
        return classCount;
    }

    //toString method for the Flight class which returns the details about the Flight (Overrides the default toString method)
    @Override
    public String toString() {
        return  '\n' + "-------------" + '\n' +
                "* Flight #" + flightNumber + " *" + '\n' +
                "-------------" + '\n' +
                "From: " + startLocation + '\n' +
                "To: " + endLocation + '\n' +
                "Distance: " + distance + '\n' +
                "First Class Passengers: " + ClassTotalSeats("F") + '\n' +
                "Economy Class Passengers: " + ClassTotalSeats("E") + '\n' +
                "Unallocated seats: " + totalFreeSeats() + '\n' +
                //Used a regular expression to replace square brackets from the CrewMember toString() output with nothing.
                "Crew: " + crew.toString().replaceAll("\\[|\\]", "") + '\n' +
                craft.toString();
    }

    //Getter for flightNumber
    public int getFlightNumber() { return flightNumber; }

    //Setter for flightNumber
    public void setFlightNumber(int flightNumber) { this.flightNumber = flightNumber; }

    //Getter for craft
    public Aircraft getCraft() { return craft; }

    //Setter for craft
    public void setCraft(Aircraft craft) { this.craft = craft; }

    //Getter for startLocation
    public String getStartLocation() { return startLocation; }

    //Setter for startLocation
    public void setStartLocation(String startLocation) { this.startLocation = startLocation; }

    //Getter for endLocation
    public String getEndLocation() { return endLocation; }

    //Setter for endLocation
    public void setEndLocation(String endLocation) { this.endLocation = endLocation; }

    //Getter for distance
    public double getDistance() { return distance; }

    //Setter for distance
    public void setDistance(double distance) { this.distance = distance; }
}
