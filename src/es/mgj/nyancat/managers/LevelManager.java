package es.mgj.nyancat.managers;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import es.mgj.nyancat.characters.Allied;
import es.mgj.nyancat.characters.Rainbow;
import es.mgj.nyancat.main.NyanCat;
import es.mgj.nyancat.screens.GameOverScreen;
import es.mgj.nyancat.screens.GameScreen;
import es.mgj.nyancat.util.Constantes;

public class LevelManager {
	
	SpriteManager spriteManager;
	private String[] steps;
	int tasaIkeas;
	
	public LevelManager(SpriteManager spriteManager){
		this.spriteManager = spriteManager;
		
		tasaIkeas = 8;
	}

	public void generateRainbow() {
		
		SpriteManager.rainbowIndex ++;
		
		Rainbow rainbow;
		
		if(SpriteManager.rainbowIndex % 2 == 0){
			rainbow = new Rainbow(ResourceManager.getAnimacion("rainbowP"), 
				0, 0 , 0);
		}else{
			rainbow = new Rainbow(ResourceManager.getAnimacion("rainbowI"), 
					0, 0 , 0);
		}
		
		rainbow.setNyanCat(spriteManager.nyanCat);
		
		rainbow.index = SpriteManager.rainbowIndex;
		
		
		SpriteManager.rainbows.add(rainbow);
		
		
	}
	
	public void generateMuffinCat(){
		
		Random randomGenerator = new Random();
		
		int randomY = randomGenerator.nextInt(Constantes.SCREEN_HEIGHT - 60) + 30;
		
		int randomSpeed = randomGenerator.nextInt(250) + 300;
		
		Allied allie = new Allied(ResourceManager.getAnimacion("muffincat"), 
				Constantes.SCREEN_WIDTH, randomY , randomSpeed);
		
		allie.tipo = "muffincat";
		
		int randomChance = randomGenerator.nextInt(1500);
		
		if(randomChance <= 8)
			spriteManager.allies.add(allie);
	}

	public void generateAllie() {
		
		Random randomGenerator = new Random();
		
		int randomY = randomGenerator.nextInt(Constantes.SCREEN_HEIGHT - 60) + 30;
		
		int randomSpeed = randomGenerator.nextInt(350) + 150;
		
		Allied allie = new Allied(ResourceManager.getAnimacion("goodguy"), 
				Constantes.SCREEN_WIDTH, randomY , randomSpeed);
		
		allie.tipo = "cat";
		
		int randomChance = randomGenerator.nextInt(1500);
		
		if(randomChance <= 15)
			spriteManager.allies.add(allie);
	}

	public void generateIkea() {
		
		Random randomGenerator = new Random();
		
		int randomY = randomGenerator.nextInt(Constantes.SCREEN_HEIGHT);
		
		int randomX = randomGenerator.nextInt(Constantes.SCREEN_WIDTH);
		
		Allied allie = new Allied(ResourceManager.getAnimacion("ikea"), 
				randomX, randomY , 5f);
		
		allie.tipo = "ikea";
		
		allie.animation.setPlayMode(Animation.NORMAL);
		allie.collidable = false;
		
		int randomChance = randomGenerator.nextInt(1500);
		
		if(randomChance <= tasaIkeas)
			spriteManager.allies.add(allie);
	}
	
	public void generateSpeed() {
		
		Random randomGenerator = new Random();
		
		int randomY = randomGenerator.nextInt(Constantes.SCREEN_HEIGHT);
		
		int randomSpeed = randomGenerator.nextInt(750) + 500;
		
		Allied allie = new Allied(ResourceManager.getAnimacion("speed"), 
				Constantes.SCREEN_WIDTH, randomY , randomSpeed);
		
		allie.tipo = "speed";
		
		int randomChance = randomGenerator.nextInt(1500);
		
		if(randomChance <= 5)
			spriteManager.allies.add(allie);
	}
	
	public void drawOnscreenText() {
		
		GameScreen.game.font.draw(GameScreen.game.batch, "RAINBOWS LEFT: " + spriteManager.rainbows.size, 0, 20);
		GameScreen.game.font.draw(GameScreen.game.batch, "NIVEL: " + GameScreen.tipo , 0, 40);
		GameScreen.game.font.draw(GameScreen.game.batch, "BADY GUY´S LIFES: " + spriteManager.enemy.lives, 0, 60);
		
	}
	
	public boolean readFile(){
		try{
			
			FileHandle file = Gdx.files.internal("mapas/Map" + NyanCat.currentLevel + ".txt");
			String levelInfo = file.readString();
			
			steps = levelInfo.split("\n");
			
		}catch(GdxRuntimeException gdxe){
			
			GameScreen.game.setScreen(new GameOverScreen(GameScreen.game, "Victoria!!"));
			return false;
			
		}
		return true;
		
		
	}
	
	public void generateLevelFromFile(){
		
		this.steps[0] = this.steps[0].replace("\n", "").replace("\r", "");
		this.steps[1] = this.steps[1].replace("\n", "").replace("\r", "");
		
		this.tasaIkeas = Integer.parseInt(this.steps[0]);
		
		int x = 950, y = 502, speed = Integer.parseInt(this.steps[1]);
		
		for(int i = 2 ; i < 12 ; i++){
			String[] linea = steps[i].split("");
			
			for(int l = 1 ; l < linea.length ; l++){
				
				if(linea[l].equals("g")){
					Allied allie = new Allied(ResourceManager.getAnimacion("goodguy"), 
							x,  y, speed);
					
					allie.tipo = "cat";
					spriteManager.allies.add(allie);
				}
				
				x += 40;
				
			}
			
			x = 950;

			y -= 55;
		}
		
	}
	

}
