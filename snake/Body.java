/*
Design a class called Coordinates in a filed called Coordinates.java. The class will hold two instance variables, two constructors, and four other methods.
Create two private integer variables, x and y. These will represent coordinates within the world map.
Write two constructors. The default constructor should set both x and y to 0. The parameterized constructor should take two integers as arguments and set the instance variables accordingly.
Write the following methods:
Write two accessors (getX and getY), one for each instance variable.
Write a mutator (setCoordinates) that takes two integer arguments and sets the x and y instance variables accordingly. You will not concern yourself with whether the owner of these coordinates is out-of-bounds in this method.
Write a toString method that returns the coordinates as a String representation of the current position on the map. You will report the row in letters and column in numbers. You can assume there will be no maps created that have more than 26 rows. An example is provided below.
 “C8” // x = 7, y = 2
*/
// Path: Coordinates.java

public class Coordinates {
    private int x;
    private int y;
    
    public Coordinates() {
        x = 0;
        y = 0;
    }
    
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return (char)(y + 65) + "" + (x + 1);
    }
}