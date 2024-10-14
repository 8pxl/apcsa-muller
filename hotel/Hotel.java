package hotel;

import java.text.DecimalFormat;

public class Hotel {

    private String hotelName;
    private Room[] rooms;
    private int totalRooms;

    public Hotel(String hotelName, int totalRooms, int numFloors) {
        this.hotelName = hotelName;
        this.totalRooms = totalRooms;
        rooms = new Room[totalRooms];

        int rpf = totalRooms / numFloors;
        int numQueens = rpf - 5;
        int q;
        for (int f = 0; f < numFloors; f++) {
            q = f * rpf;
            for (int i = 0; i < numQueens; i++) {
                rooms[i + q] = new Room((f+1) * 100 +i, "double queen");
            }
    
            for (int i = 0; i < 4; i++) {
                rooms[i + numQueens + q] = new Room((f+1) * 100 + i + numQueens, "single king");
            }
            rooms[rpf-1 + q] = new Room((f+1) * 100 + 4 + numQueens, "suite");
        }

    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public int getNumberOccupied() {
        int count = 0;
        for (Room room : rooms) {
            if (room.getDaysRented() <= 0) {
                count++;
            }
        }
        return count;
    }

    public double getOccupancyRate() {
        int totalRooms = getTotalRooms();
        int occupiedRooms = getNumberOccupied();
        double occupancyRate = (double) occupiedRooms / totalRooms;
        return Double.parseDouble(String.format("%.2f", occupancyRate));
    }

    public boolean rentRoom(String roomType, String renterName, int numDays) {
        for (Room room : rooms) {
            if ((room.getDaysRented()) <= 0 && room.getRoomType().equals(roomType)) {
                room.setOccupant(renterName, numDays);
                return true;
            }
        }
        return false;
    }

    public void advanceDay() {
        for (Room room : rooms) {
            room.advanceDay();
        }
    }

    public String toString() {
        return hotelName + ": " + (1.0 - getOccupancyRate()) * 100 + "% occupied";
    }

    public void printRooms() {
        for (Room room : rooms) {
            System.out.println(room.toString());
        }
    }
}

