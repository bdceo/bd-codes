package com.bdsoft.bdceo.j2se.io;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Player {
 
	public static void main(String[] args) throws Exception {
		URL url=new URL("file:///d:\\bd.mp3");
        AudioClip ac=Applet.newAudioClip(url);
        ac.play();
	}

}
