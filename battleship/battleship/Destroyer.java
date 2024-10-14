package battleship;

public class Destroyer extends Boat implements Attacker {
    
    public Destroyer(int team, Coordinates location, int direction) {

        super(team, location, direction, 2, 2, 1);
    }

    public String getID() {return "D" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Destroyer:\n";
        str += "1. Move\n2. Turn left\n3. Turn right\n4. Attack";
        return str;
    }

    public String act(int[] actions, World map) {

        String str = "";

        for (int action : actions) {
            if (action == 1) {
                str += move(map) + "\n";
            } else if (action == 2) {
                str += turn(-1) + "\n";
            } else if (action == 3) {
                str += turn(1) + "\n";
            } else {
                str += attack(map) + "\n";
            }
        }

        return str;
    }

    public String attack(World w) {
		switch (super.getDirection()) {
			case 0: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX(), super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently\n";
			case 1: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently\n";
			case 2: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY());
				if (w.isLocationOccupied(cor)) {
					return "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently\n";
			case 3: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() + i, super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently\n";
			case 4: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX(), super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently\n";
			case 5: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY() + i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently\n";
			case 6: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY());
				if (w.isLocationOccupied(cor)) {
					return "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently\n";
			case 7: for (int i = 1; i <= super.getVision(); i++) {
				Coordinates cor = new Coordinates(super.getLocation().getX() - i, super.getLocation().getY() - i);
				if (w.isLocationOccupied(cor)) {
					return "\n" + w.getOccupant(cor).takeHit(getStrength(), w);
				}
			} return "\n" + "There are no boats in range currently\n";
		} return "uh oh";
	}

    public String takeHit(int numAttacks) {

        int attacksAvoided = 0;

        for (int i = 0; i < numAttacks; i++) {
            if (Math.random() > .5) {attacksAvoided++;}
        }

        numAttacks -= attacksAvoided;

        if (numAttacks== 0) {
            return getID() + " has avoided the attack!";
        } else {
            return super.takeHit(numAttacks, null);
        }
    }
}