package System;

import java.util.HashSet;
import java.util.Set;

public class GameData {
	public static final int maxSkins = 2;
private int skinID;
private int score = 0;
private int life = 3;
private Set<Integer> unlockedSkins = new HashSet<>();

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
public void unlockSkin(int id) {
	unlockedSkins.add(id);
}
public boolean isUnlocked(int id) {
	return unlockedSkins.contains(id);
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
