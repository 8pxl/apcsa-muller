package battleship;

public class AircraftCarrier extends Boat implements Attacker{
    
    private boolean hasPlanes;
    private int successRate = 1;

    public AircraftCarrier(int team, Coordinates location, int direction) {

        super(team, location, direction, 5, 1, 1);
        this.hasPlanes = true;
    }

    public String getID() {return "A" + getTeam();}

    public String getActions() {

        String str = "Choose any of the following actions for the Aircraft Carrier:\n";
        str += "1. Move\n2. Turn left\n3. Turn right";
        str += (hasPlanes) ? "\n4. Launch planes" : "";
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
		String res = "";
		if (hasPlanes) {
			int oldsucc = successRate;
			for (int i = getLocation().getX() - getVision(); i <= getLocation().getX() + getVision(); i++) {
				for (int j = getLocation().getY() - getVision(); j <= getLocation().getY() + getVision(); j++) {
					Coordinates c = new Coordinates(i, j);
					if (w.isLocationOccupied(c) && w.getOccupant(c) != this) {
						res += "Air raid! ";
						res += w.getOccupant(c).takeHit(getStrength(), w);
						successRate *= 0.8;
						if (Math.random() > successRate) {
							hasPlanes = false;
							res += "\nThe planes have been destroyed.";
							return "\n" + res;
						}
					}
				}
			}
			if (successRate == oldsucc) res += "\nThere are no boats in range currently.";
		} else res += "\n" + getID() + " has no planes remaining.";
		return "\n" + res;
	}
}