package System;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.ponsit.dopong3.Main;

public class TextLoader {
	Main game;
String[] paths = {"csv/text/Info.csv"};
Array<String> desc;
FileHandle file;
public TextLoader(Main game,int id) {
	this.game = game;
	this.file = Gdx.files.internal(paths[id]);
}
public void loadText() {
	String[] dialog = file.readString().split("\\r?\\n");
	System.out.println(dialog);
	desc = new Array<String>();
	String pongDesc = "";
	for(int i = 0; i < dialog.length;i++) {
		pongDesc += dialog[i] + "\n";
		if(pongDesc.contains("|")) {
			pongDesc = pongDesc.replace("|", "");
			desc.add(pongDesc);
			pongDesc = "";
		}
	}
}
public Array<String> getDesc() {
	return desc;
}
}
