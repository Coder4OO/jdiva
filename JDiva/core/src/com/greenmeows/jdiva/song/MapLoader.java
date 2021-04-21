package com.greenmeows.jdiva.song;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MapLoader {
	private float speed;
	private float duration;
	private float spawndelay;
	private String name;
	private Music music;
	private Scanner scanner;
	private String folderpath;
	private Sprite bg;

	private String musicpath;
	
	private ArrayList<Note> notes = new ArrayList<Note>();
	private ArrayList<Integer> skips = new ArrayList<Integer>();
	
	private void load_data(String path) {
		File file = new File(path);
		int linenumber = 0;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(line.contains(":")) {
				//info line
				String metadata = line.split(":")[0];
				String info = line.split(":")[1];
				switch(metadata.toLowerCase()) {
				case "name":
					this.name = info;
					break;
				case "speed":
					this.speed = Float.parseFloat(info);
					break;
				case "duration":
					this.duration = Float.parseFloat(info);
					break;
				case "spawndelay":
					this.spawndelay = Float.parseFloat(info);
					break;
				case "music":
					this.musicpath = this.folderpath+"\\"+info;
					this.music = Gdx.audio.newMusic(new FileHandle(new File(this.musicpath)));
					break;
				case "background":
					this.bg = new Sprite(new Texture(this.folderpath+"\\"+info));
					break;
				}
			}
			else if(line.contains("skip line")) {
				skips.add(linenumber);
			}
			else {
				String[] split = line.split(",");
				ArrayList<String> params = new ArrayList<String>();
				for(int i = 0; i < split.length; i++) {
					String data = split[i];
					params.add(data);
				}
				float x = Float.parseFloat(params.get(0));
				float y = Float.parseFloat(params.get(1));
				float cx = Float.parseFloat(params.get(2));
				float cy = Float.parseFloat(params.get(3));
				float bpm = getSpeed();
				String keybind = params.get(4);
				Note note = new Note(x, y, cx, cy, bpm, keybind);
				notes.add(note);
			}
			linenumber++;
		}
		scanner.close();
	}
	
	
	protected float getSpeed() {
		return speed;
	}

	protected float getDuration() {
		return duration;
	}

	protected float getSpawndelay() {
		return spawndelay;
	}

	protected String getName() {
		return name;
	}

	protected Music getMusic() {
		return music;
	}
	
	protected Sprite getBackground() {
		return bg;
	}
	
	protected ArrayList<Note> getNotes(){
		return notes;
	}
	
	protected ArrayList<Integer> getSkips(){
		return skips;
	}
	
	public MapLoader(String path) {
		this.folderpath = path;
		load_data(this.folderpath+"\\"+"beatmap.txt");
	}

	
}
