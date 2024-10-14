package battleship;

public class NotBattleship extends Boat implements Attacker {
    
    public NotBattleship(int team, Coordinates location, int direction) {

        super(team, location, direction, 4, 3, 1);
    }

    public String getID() {return "B" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Battleship:\n";
        str += "1. Move\n2. Turn left\n3. Turn right\n4. Attack";
        return str;
    }

    public String act(int[] actions, World map) {

        int action = actions[0];
        String str = "";

        if (action == 1) {str += move(map);}
        else if (action == 2) {str += turn(-1);}
        else if (action == 3) {str += turn(1);}
        else if (action == 4) {str += attack(map);}

        return str;
    }

    public String attack(World w) {
		switch (super.getDirection()) {
			case 0: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX(), super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + "Fire cannons!\n" + w.getOccupant(cor).takeHit(getStrength(), w) + "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 1: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + "Fire cannons!\n" + w.getOccupant(cor).takeHit(getStrength(), w) + "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 2: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY());
				if (w.isLocationOccupied(cor)) {
					return "\n" + "Fire cannons!\n" + w.getOccupant(cor).takeHit(getStrength(), w) + "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 3: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + "Fire cannons!\n" + w.getOccupant(cor).takeHit(getStrength(), w) + "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 4: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX(), super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + "Fire cannons!\n" + w.getOccupant(cor).takeHit(getStrength(), w) + "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 5: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + "Fire cannons!\n" + w.getOccupant(cor).takeHit(getStrength(), w) + "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 6: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY());
				if (w.isLocationOccupied(cor)) {
					return "\n" + "Fire cannons!\n" + w.getOccupant(cor).takeHit(getStrength(), w) + "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently.";
			case 7: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + "Fire cannons!\n" + w.getOccupant(cor).takeHit(getStrength(), w) + "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently.";
		} return "uh oh";
	}

}