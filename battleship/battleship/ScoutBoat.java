package battleship;

public abstract class ScoutBoat extends Boat {
    
    public ScoutBoat(int team, Coordinates location, int direction, int health, int vision) {

        super(team, location, direction, health, 1, vision);
    }

    String takeHit(int num, World w) {
		if ((int)(Math.random() * 4) == 0) {
			return super.takeHit(num, w);
		} else return "\n" + super.toString() + " has avoided the attack!";
	}
}