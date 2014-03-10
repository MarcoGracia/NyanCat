package es.mgj.nyancat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.mgj.nyancat.main.NyanCat;
import es.mgj.nyancat.managers.LevelManager;
import es.mgj.nyancat.managers.ResourceManager;
import es.mgj.nyancat.managers.SpriteManager;

public class GameScreen implements Screen {

	public static NyanCat game;
	LevelManager levelManager;
	SpriteManager spriteManager;
	public static String tipo;
	public static Sound wavSound;
	
	public GameScreen(NyanCat game, String tipo) {
		
		GameScreen.tipo = tipo;
		this.game = game;
		
		this.game.paused = false;
		
		Texture.setEnforcePotImages(false);
		
		ResourceManager.loadAllResources();
		spriteManager = new SpriteManager(this);
		
		levelManager = new LevelManager(spriteManager);
		
		
		if(tipo.equals("constructed")){
			levelManager.readFile();
			
			levelManager.generateLevelFromFile();
		}
			
	}
	
	
	@Override
	public void show() {
		
		if (game.configurationManager.isSoundEnabled()){
			wavSound = ResourceManager.getSound("musica");
			wavSound.play();
			wavSound.loop();
		}
			
	}
	
	@Override
	public void render(float dt) {
		
		this.spriteManager.update(dt);
		
		this.spriteManager.render(game.batch);
		
	}

	@Override
	public void dispose() {
		
	}

	/**
	 * Cuando esta pantalla se oculta, se pausa
	 */
	@Override
	public void hide() {
		
		game.paused = true;
	}

	@Override
	public void pause() {
		
		game.paused = true;
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
		game.paused = false;
	}
	
	
	

}
