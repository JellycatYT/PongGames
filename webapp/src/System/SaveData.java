package System;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveData {
private Preferences prefs;
public SaveData() {
	prefs = Gdx.app.getPreferences("DoDoPong4");
}
public void save(GameData data) {
	prefs.putInteger("score", data.getScore());
	prefs.putInteger("skinID", data.getSkinID());
	for(int i = 0; i < CsvLoader.Levels.length;i++) {
		prefs.putBoolean("level_" + i, data.isLevelCleared(i));
	}
	prefs.flush();
}
public void load(GameData data) {
	data.setScore(prefs.getInteger("score",0));

	 for(int i = 0; i < data.skins.length; i++) {
	        prefs.putBoolean("skin_" + i, data.isUnlocked(i));
	    }

for(int i = 0; i < CsvLoader.Levels.length;i++) {
	if(prefs.getBoolean("level_" + i,false)) {
		data.clearLevel(i);
	}
}
}
}