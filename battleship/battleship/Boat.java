package battleship;

public abstract class Boat {
	private int team;
	private Coordinates location;
	private int direction;
	private int health;
	private int strength;
	private int vision;
	
	Boat(int t, Coordinates l, int d, int h, int s, int v) {
		team = t;
		location = l;
		direction = d;
		health = h;
		strength = s;
		vision = v;
	}
	
	int getTeam() {
		return team;
	}
	
	Coordinates getLocation() {
		return location;
	}
	
	int getDirection() {
		return direction;
	}
	
	char getDirectionSymbol() {
		if (direction == 0) return '\u2191';
		if (direction == 1) return '\u2197';
		if (direction == 2) return '\u2192';
		if (direction == 3) return '\u2198';
		if (direction == 4) return '\u2193';
		if (direction == 5) return '\u2199';
		if (direction == 6) return '\u2190';
		if (direction == 7) return '\u2196';
		return 'a';
	}
	
	int getHealth() {
		return health;
	}
	
	int getStrength() {
		return strength;
	}
	
	int getVision() {
		return vision;
	}
	
	abstract String getID();
	
	abstract String act(int arr[], World w);
	
	abstract String getActions();
	
	String move(World w) {
		Coordinates newLocation = w.getAdjacentLocation(location, direction);
		if (w.isLocationValid(newLocation)) {
			if (!w.isLocationOccupied(newLocation)) {
				Coordinates oldLocation = location;
				w.setOccupant(null, oldLocation);
				w.setOccupant(this, newLocation);
				location = newLocation;
				return "\n" + this + " moves from " + oldLocation + " to " + newLocation;
			} else return "\n" + this + " cannot move to " + newLocation + " as it is occupied.";
		} else return "\n" + this + " cannot move off the map.";
	}
	
	String turn(int d) {
		if (d < 0) {
			if (direction > 0) direction--;
			else direction = 7;
			String newDir = "";
			switch (direction) {
			case 0: 
				newDir = "N";
				break;
			case 1: 
				newDir = "NE";
				break;
			case 2: 
				newDir = "E";
				break;
			case 3: 
				newDir = "SE";
				break;
			case 4: 
				newDir = "S";
				break;
			case 5: 
				newDir = "SW";
				break;
			case 6: 
				newDir = "W";
				break;
			case 7: 
				newDir = "NW";
				break;
			}
			return "\n" + this + " turned left, now facing " + newDir;
		} else if (d > 0) {
			if (direction < 7) direction++;
			else direction = 0;
			String newDir = "";
			switch (direction) {
			case 0: 
				newDir = "N";
				break;
			case 1: 
				newDir = "NE";
				break;
			case 2: 
				newDir = "E";
				break;
			case 3: 
				newDir = "SE";
				break;
			case 4: 
				newDir = "S";
				break;
			case 5: 
				newDir = "SW";
				break;
			case 6: 
				newDir = "W";
				break;
			case 7: 
				newDir = "NW";
				break;
			}
			return "\n" + this + " turned right, now facing " + newDir;
		}
		return "";
	}
	
	String takeHit(int s, World w) {
		health -= s;
		if (health < 0) health = 0;
		
		if (health == 0) {
			w.setOccupant(null, location);
			return this + " has been sunk!";
		} else return this + " takes " + s + " damage.";
	}
	
	void setLocation(Coordinates c) {
		location = c;
	}
	
	public String toString() {
		return getID();
	}

}