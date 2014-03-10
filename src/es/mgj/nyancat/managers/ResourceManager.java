package es.mgj.nyancat.managers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ResourceManager {

	private static Map<String, Animation> animations = new HashMap<String, Animation>();
	
	private static Map<String, TextureAtlas> atlas = new HashMap<String, TextureAtlas>();
	
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	
	public static void loadAllResources(){
		
		loadResource("characters", new TextureAtlas(
				Gdx.files.internal("characteres/nyan_characters.atlas")));
		
		loadSound("musica", Gdx.audio.newSound(Gdx.files.internal("sonidos/Nyan Cat.mp3")));
		
	}
	
	public static void loadResource(String name, TextureAtlas animation){
		atlas.put(name, animation);
		
		animations.put("background",
				new Animation(0.01f, getAtlas("characters").findRegion("FONDO")));
		
		animations.put("speed",
				new Animation(0.01f, getAtlas("characters").findRegion("speed")));
		
		animations.put("muffincat",
				new Animation(0.01f, getAtlas("characters").findRegion("muffincat")));
		
		Animation ikea = 
		new Animation(0.40f,
		getAtlas("characters").findRegion("ikea1"),
		getAtlas("characters").findRegion("ikea2"),
		getAtlas("characters").findRegion("ikea3"),
		getAtlas("characters").findRegion("ikea4"),
		getAtlas("characters").findRegion("ikea5"),
		getAtlas("characters").findRegion("ikea6"),
		getAtlas("characters").findRegion("ikea7"),
		getAtlas("characters").findRegion("ikea8"));
		
		ikea.setPlayMode(Animation.NORMAL);
		
		animations.put("ikea", ikea);
		
		animations.put("countdown",
				new Animation(1f,
				getAtlas("characters").findRegion("5"),
				getAtlas("characters").findRegion("4"),
				getAtlas("characters").findRegion("3"),
				getAtlas("characters").findRegion("2"),
				getAtlas("characters").findRegion("1"),
				getAtlas("characters").findRegion("0"),
				getAtlas("characters").findRegion("0"),
				getAtlas("characters").findRegion("0")));
		
		animations.put("rainbowP",
				new Animation(0.1f,
				getAtlas("characters").findRegion("RAINBOW2"),
				getAtlas("characters").findRegion("RAINBOW"),
				getAtlas("characters").findRegion("RAINBOW1")));
		
		animations.put("rainbowI",
				new Animation(0.1f,
				getAtlas("characters").findRegion("RAINBOW1"),
				getAtlas("characters").findRegion("RAINBOW"),
				getAtlas("characters").findRegion("RAINBOW2")));
		
		animations.put("nyancat",
				new Animation(0.25f, 
				getAtlas("characters").findRegion("NYANCATRAINBOW")));
		
		animations.put("badguy",
				new Animation(0.25f, 
				getAtlas("characters").findRegion("badguy1"),
				getAtlas("characters").findRegion("badguy2"),
				getAtlas("characters").findRegion("badguy3"),
				getAtlas("characters").findRegion("badguy4"),
				getAtlas("characters").findRegion("badguy5"),
				getAtlas("characters").findRegion("badguy6")));
		
		animations.put("goodguy",
				new Animation(0.25f, 
				getAtlas("characters").findRegion("goodguy1"),
				getAtlas("characters").findRegion("goodguy2"),
				getAtlas("characters").findRegion("goodguy3"),
				getAtlas("characters").findRegion("goodguy4")));
			
	}
	
	
	public static TextureAtlas getAtlas(String name){
		
		return atlas.get(name);
	}
	
	public static Animation getAnimacion(String name){
		return animations.get(name);
	}
	
	public static Sound getSound(String name){
		
		return sounds.get(name);
	}
	
	public static void loadSound(String name, Sound sound){
		sounds.put(name, sound);
		
	}
}
