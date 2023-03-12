public class Seat {
    //Seat class
    private int row;
    private int seat;
    private String flyingClass;
    private Passenger allocatedTo;

    //Constructor for the Seat class
    public Seat(int row, int seat, String flyingClass) {
        //Sets up the row, seat, and flyingClass variables in the constructor as they are private
        this.row = row;
        this.seat = seat;
        this.flyingClass = flyingClass;
    }

    //Getter for row
    public int getRow() {
        return row;
    }

    //Setter for row
    public void setRow(int row) {
        this.row = row;
    }

    //Getter for seat
    public int getSeat() {
        return seat;
    }

    //Setter for seat
    public void setSeat(int seat) {
        this.seat = seat;
    }

    //Getter for flyingClass
    public String getFlyingClass() {
        return flyingClass;
    }

    //Setter for flyingClass
    public void setFlyingClass(String flyingClass) {
        this.flyingClass = flyingClass;
    }

    //Getter for allocatedTo
    public Passenger getAllocatedTo() {
        return allocatedTo;
    }

    //Setter for allocatedTo
    public void setAllocatedTo(Passenger allocatedTo) {
        this.allocatedTo = allocatedTo;
    }
}
