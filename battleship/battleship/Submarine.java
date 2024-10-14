package battleship;

import java.util.Random;

public class Submarine extends ScoutBoat implements Attacker {

    private int numOfTorpedoes;

    public Submarine(int team, Coordinates location, int direction, int numOfTorpedoes) {

        super(team, location, direction, 3, 2);
        this.numOfTorpedoes = numOfTorpedoes;
    }

    public String getID() {return "S" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Submarine:\n";
        str += "1. Move\n2. Turn left\n3. Turn right\n4. Submerge";
        str += (numOfTorpedoes > 0) ? "\n5. Fire torpedoes" : "";
        return str;
    }

    public String act(int[] actions, World map) {

        int action = actions[0];
        String str = "";

        if (action == 1) {str += move(map);}
        else if (action == 2) {str += turn(-1);}
        else if (action == 3) {str += turn(1);}
        else if (action == 4) {str += submerge(map);}
        else if (action == 5) {str += attack(map);}

        return str;
    }

    public Coordinates generateSquare(World map) {

        int currentRow = getLocation().getY();
        int currentCol = getLocation().getX();

        Random rand = new Random();
        
        int newRow = rand.nextInt(map.getHeight());
        int newCol = rand.nextInt(map.getWidth());

        Coordinates coord = new Coordinates(newCol, newRow);

        if (Math.abs(newRow - currentRow) > 2 && Math.abs(newCol - currentCol) > 2 && !map.isLocationOccupied(coord)) {
            return coord;
        } else {
            return generateSquare(map);
        }
    }

    public String submerge(World map) {

        String str = getID() + " moves from " + getLocation().toString() + " to ";
        Coordinates newSquare = generateSquare(map);
        map.setOccupant(this, newSquare);
        map.setOccupant(null, getLocation());
        setLocation(newSquare);

        return str += newSquare.toString();
    }

    public String attack(World w) {
		if (numOfTorpedoes > 0) {
			switch (super.getDirection()) {
			case 0: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX(), super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					int st = (int)(Math.random() * w.getOccupant(cor).getHealth()) + 1;
					return "\n" + "Fire torpedoes!\n" + w.getOccupant(cor).takeHit(st, w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 1: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					int st = (int)(Math.random() * w.getOccupant(cor).getHealth()) + 1;
					return "\n" + "Fire torpedoes!\n" + w.getOccupant(cor).takeHit(st, w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 2: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY());
				if (w.isLocationOccupied(cor)) {
					int st = (int)(Math.random() * w.getOccupant(cor).getHealth()) + 1;
					return "\n" + "Fire torpedoes!\n" + w.getOccupant(cor).takeHit(st, w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 3: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					int st = (int)(Math.random() * w.getOccupant(cor).getHealth()) + 1;
					return "\n" + "Fire torpedoes!\n" + w.getOccupant(cor).takeHit(st, w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 4: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX(), super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					int st = (int)(Math.random() * w.getOccupant(cor).getHealth()) + 1;
					return "\n" + "Fire torpedoes!\n" + w.getOccupant(cor).takeHit(st, w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 5: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					int st = (int)(Math.random() * w.getOccupant(cor).getHealth()) + 1;
					return "\n" + "Fire torpedoes!\n" + w.getOccupant(cor).takeHit(st, w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 6: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY());
				if (w.isLocationOccupied(cor)) {
					int st = (int)(Math.random() * w.getOccupant(cor).getHealth()) + 1;
					return "\n" + "Fire torpedoes!\n" + w.getOccupant(cor).takeHit(st, w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 7: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					int st = (int)(Math.random() * w.getOccupant(cor).getHealth()) + 1;
					return "\n" + "Fire torpedoes!\n" + w.getOccupant(cor).takeHit(st, w);
				}
			} return "\n" + "There are no boats in range currently.";
			}
		} return "\n" + getID() + " has no torpedoes remaining.";
	}
}