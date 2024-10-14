package battleship;

public class World {
	public Boat[][] map;
	public static int NORTH = 0;
	public static int NORTHEAST = 1;
	public static int EAST = 2;
	public static int SOUTHEAST = 3;
	public static int SOUTH = 4;
	public static int SOUTHWEST = 5;
	public static int WEST = 6;
	public static int NORTHWEST = 7;
	
	World(int w, int h) {
		if (w < 4) w = 4;
		if (w > 10) w = 10;
		if (h < 4) h = 4;
		if (h > 10) h = 10;
		map = new Boat[h][w];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				map[j][i] = null;
			}
		}
	}
	
	int getWidth() {
		return map[0].length;
	}
	
	int getHeight() {
		return map.length;
	}
	
	Boat getOccupant(Coordinates c) {
		if (isLocationValid(c)) return map[c.getY()][c.getX()];
		else return null;
	}
	
	boolean isLocationValid(Coordinates c) {
		if (c.getX() > -1 && c.getX() < getWidth() && c.getY() > -1 && c.getY() < getHeight()) return true;
		else return false;
	}
	
	boolean isLocationOccupied(Coordinates c) {
		if (getOccupant(c) != null) return true;
		else return false;
	}
	
	boolean setOccupant(Boat b, Coordinates c) {
		if (isLocationValid(c)) {
			map[c.getY()][c.getX()] = b;
			return true;
		}
		else return false;
	}
	
	Coordinates getAdjacentLocation(Coordinates c, int dir) {
		int xval = c.getX();
		int yval = c.getY();
		switch (dir) {
		case 0:
			yval--;
			break;
		case 1:
			yval--;
			xval++;
			break;
		case 2:
			xval++;
			break;
		case 3:
			xval++;
			yval++;
			break;
		case 4:
			yval++;
			break;
		case 5:
			yval++;
			xval--;
			break;
		case 6:
			xval--;
			break;
		case 7:
			xval--;
			yval--;
			break;
		}
		return new Coordinates(xval, yval);
	}
	
	String drawTeamMap(Boat barray[], int view) {
		String m = "";
		
		for (int i = 0; i < getWidth() + 1; i++) {
			if (i == 0) m += "@  ";
			else m += i + "  ";
		}
		for (int i = 0; i < getHeight(); i++) {
			m += "\n";
			m += (char)(i + 65) + " ";
			for (int j = 0; j < getWidth(); j++) {
				if (view == 1) m += "###";
				else if (view == 2) {
					boolean visible = false;
					for (int k = 0; k < barray.length; k++) {
						if (j >= barray[k].getLocation().getX() - barray[k].getVision() && 
							j <= barray[k].getLocation().getX() + barray[k].getVision() &&
							i >= barray[k].getLocation().getY() - barray[k].getVision() &&
							i <= barray[k].getLocation().getY() + barray[k].getVision()) {
							visible = true;
						}
					}
					if (visible) {
						Coordinates cur = new Coordinates(j, i);
						if (!isLocationOccupied(cur)) {
							m += "~~~";
						} else {
							m += "" + getOccupant(cur).getDirectionSymbol() + getOccupant(cur).getID();
						}
					} else m += "###";
				} else if (view == 3) {
					boolean visible = false;
					for (int k = 0; k < barray.length; k++) {
						if (j >= barray[k].getLocation().getX() - barray[k].getVision() && 
							j <= barray[k].getLocation().getX() + barray[k].getVision() &&
							i >= barray[k].getLocation().getY() - barray[k].getVision() &&
							i <= barray[k].getLocation().getY() + barray[k].getVision()) {
							visible = true;
						}
					}
					if (visible) {
						Coordinates cur = new Coordinates(j, i);
						if (!isLocationOccupied(cur)) {
							m += "~~~";
						} else {
							m += "" + getOccupant(cur).getHealth() + getOccupant(cur).getID();
						}
					} else m += "###";
				}
			}
		}
		
		return m;
	}
}