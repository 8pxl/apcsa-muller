package hotel;

public class Room {
    private int roomNumber;
    private int daysRented;
    private String roomType;
    private String occupantName;

    public Room(int num, String type) {
        roomNumber = num;
        daysRented = 0;
        occupantName = null;
        if ((type == "single king") || (type == "suite")) roomType = type;
        else roomType = "double queen"; 
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getOccupantName() {
        return occupantName;
    }

    public boolean setOccupant(String name, int days) {
        if (daysRented <= 0) {
            daysRented = days;
            occupantName = name;
            return true;
        }
        return false;
    }

    public void advanceDay() {
        daysRented --;
        if (daysRented <= 0) {
            occupantName = null;
        }
    }

    public String toString() {
        if (daysRented > 0) {
            return ("Room " + roomNumber + ": " + roomType + " - rented to " + occupantName);
        }
        return ("Room " + roomNumber + ": " + roomType + " - free"  );
    }
}
