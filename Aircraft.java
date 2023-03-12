//Imports the file java library
import java.io.File;

public class Aircraft {
    //Aircraft class
    private String make;
    private String model;
    private String tailNumber;
    private double craftWeight;
    private double maximumTakeOffWeight;
    private File layoutFile;

    //Constructor for the Aircraft class
    public Aircraft(String make, String model, String tailNumber, double craftWeight, double maximumTakeOffWeight, File layoutFile) {
        //Sets up the Aircraft variables in the constructor as they are private
        this.make = make;
        this.model = model;
        this.tailNumber = tailNumber;
        this.craftWeight = craftWeight;
        this.maximumTakeOffWeight = maximumTakeOffWeight;
        this.layoutFile = layoutFile;
    }

    //toString method for the Aircraft class which returns the details about the Aircraft (Overrides the default toString method)
    @Override
    public String toString() {
        return  "Make: " + make +
                " Model: " + model +
                " TailNumber: " + tailNumber +
                " Weight: " + craftWeight +
                " Maximum Take Off Weight: " + maximumTakeOffWeight;
    }

    //Getter for make
    public String getMake() {
        return make;
    }

    //Setter for make
    public void setMake(String make) {
        this.make = make;
    }

    //Getter for model
    public String getModel() {
        return model;
    }

    //Setter for model
    public void setModel(String model) {
        this.model = model;
    }

    //Getter for tailNumber
    public String getTailNumber() {
        return tailNumber;
    }

    //Setter for tailNumber
    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    //Getter for craftWeight
    public double getCraftWeight() {
        return craftWeight;
    }

    //Setter for craftWeight
    public void setCraftWeight(double craftWeight) {
        this.craftWeight = craftWeight;
    }

    //Getter for maximumTakeOffWeight
    public double getMaximumTakeOffWeight() {
        return maximumTakeOffWeight;
    }

    //Setter for maximumTakeOffWeight
    public void setMaximumTakeOffWeight(double maximumTakeOffWeight) { this.maximumTakeOffWeight = maximumTakeOffWeight; }

    //Getter for the crafts layoutFile
    public File getLayoutFile() {
        return layoutFile;
    }

    //Setter for the crafts layoutFile
    public void setLayoutFile(File layoutFile) {
        this.layoutFile = layoutFile;
    }
}
