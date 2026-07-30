package System;

public class GameData {
private int skinID;
private int score = 0;
private int life = 3;
public int getSkinID() {
	return skinID;
}
public void setSkinID(int skinID) {
	this.skinID = skinID;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public void addScore(int add) {
score += add;
}
public int getLife() {
	return life;
}
public void setLife(int life) {
	this.life = life;
}
public void addLife(int add) {
life += add;
}
}
