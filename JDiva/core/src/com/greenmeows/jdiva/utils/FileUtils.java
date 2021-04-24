package com.greenmeows.jdiva.utils;

import java.io.File;
import java.util.ArrayList;

import com.greenmeows.jdiva.song.Song;

public class FileUtils {
	
	public static String getSongFolder() {
		File pwd = new File(System.getProperty("user.dir"));
		String mainfolder = pwd.getParentFile().toString();
		return mainfolder+"\\"+"core"+"\\"+"assets"+"\\"+"songs";
	}
	
	public static String getFontFolder() {
		File pwd = new File(System.getProperty("user.dir"));
		String mainfolder = pwd.getParentFile().toString();
		return mainfolder+"\\"+"core"+"\\"+"assets"+"\\"+"fonts";
	}

	public static ArrayList<Song> getAllSongs() {
		File songfolder = new File(getSongFolder());
		File[] files = songfolder.listFiles();
		ArrayList<Song> songs = new ArrayList<Song>();
		for(File file : files) {
			songs.add(new Song(file.getName()));
		}
		return songs;
	}
	
	
}

