package com.greenmeows.jdiva.song;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.greenmeows.jdiva.utils.FileUtils;

public class Song {
	
	private float speed;
	private String name;
	private Music music;
	private float duration;
	private float spawndelay;
	private Sprite bg;
	private ArrayList<Note> notes = new ArrayList<Note>();
	private ArrayList<Integer> skips = new ArrayList<Integer>();
	
	public Song(String songname) {
		String path = FileUtils.getSongFolder()+"\\"+songname;
		MapLoader loader = new MapLoader(path);
		this.name = loader.getName();
		this.speed = loader.getSpeed();
		this.duration = loader.getDuration();
		this.spawndelay = loader.getSpawndelay();
		this.music = loader.getMusic();
		this.bg = loader.getBackground();
		this.notes = loader.getNotes();
		this.skips = loader.getSkips();
	}

	public float getSpeed() {
		return speed;
	}

	public String getName() {
		return name;
	}

	public Music getMusic() {
		return music;
	}

	public float getDuration() {
		return duration;
	}

	public float getSpawndelay() {
		return spawndelay;
	}
	
	public Sprite getBackground() {
		return bg;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public ArrayList<Integer> getSkips(){
		return skips;
	}
	
	
}
