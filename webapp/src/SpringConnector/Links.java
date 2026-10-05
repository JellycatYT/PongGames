package SpringConnector;

public enum Links {
GOOGLE(0), YOUTUBE(1), SPRING(2),
POST(3);
	public int id;

	Links(int id) {
		this.id = id;
	}
	public int getID() {
		return id;
	}
}
