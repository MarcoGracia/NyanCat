package es.mgj.nyancat.managers;


import java.awt.event.KeyEvent;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import es.mgj.nyancat.characters.Allied;
import es.mgj.nyancat.characters.Enemy;
import es.mgj.nyancat.characters.NyanCat;
import es.mgj.nyancat.characters.Rainbow;
import es.mgj.nyancat.screens.GameOverScreen;
import es.mgj.nyancat.screens.GameScreen;
import es.mgj.nyancat.screens.InGameMenuScreen;
import es.mgj.nyancat.util.Constantes;

public class SpriteManager implements InputProcessor{

	public NyanCat nyanCat;
	public Enemy enemy;
	public Array <Allied>allies;
	public static Array <Rainbow>rainbows;
	public Allied background;
	public static int rainbowIndex;
	public LevelManager levelManager;
	public GameScreen gamescreen;
	public Allied countDown;
	
	public SpriteManager(GameScreen gamescreen){
		
		this.gamescreen = gamescreen;
		
		nyanCat = new NyanCat(ResourceManager.getAnimacion("nyancat"), 75, Constantes.SCREEN_HEIGHT /2, 0);
		
		enemy = new Enemy(ResourceManager.getAnimacion("badguy"), -75 ,  Constantes.SCREEN_HEIGHT /2, 1);
		
		background = new Allied(ResourceManager.getAnimacion("background"), 0,
				 0, 0);
		
		countDown = new Allied(ResourceManager.getAnimacion("countdown"), 0,
				 0, 0);
		this.enemy.setCountDown(countDown);
		
		this.enemy.setNyancat(nyanCat);
		
		if(GameScreen.tipo.equals("constructed"))
			this.enemy.lives = 10;
		
		this.nyanCat.setEnemy(enemy);
		
		allies = new Array<Allied>();
		rainbows = new Array<Rainbow>();
		
		this.enemy.setRainbows(rainbows);
		
		rainbowIndex = 0;
		
		levelManager = new LevelManager(this);
		
		levelManager.generateRainbow();
		levelManager.generateRainbow();
		
	}
	
	public void update(float dt){
		
		Gdx.input.setInputProcessor(this);
		
		if (!GameScreen.game.paused) {
			
			if(!GameScreen.tipo.equals("constructed"))
				levelManager.generateAllie();
			
			levelManager.generateIkea();
			
			levelManager.generateSpeed();
			
			levelManager.generateMuffinCat();
			
			handleInputs(dt);
			
			background.update(dt);
			
			nyanCat.update(dt);
			
			countDown.update(dt);
			
			if(enemy.lives > 0)
				enemy.update(dt, nyanCat);
			
			for(Allied a : allies){
				a.update(dt);
				checkBuffCollisions(a);
			}
			
			for(Rainbow r : rainbows){
				r.update(dt);
				
			}
		}
		
		
	}

	private void handleInputs(float dt) {
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			
			nyanCat.move(new Vector2(-dt, 0));
		}
		else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			
			nyanCat.move(new Vector2(dt, 0));
			enemy.sprint(dt);
			enemy.stabilize(dt);
		}
		else if (Gdx.input.isKeyPressed(Keys.UP)) {
			
			nyanCat.move(new Vector2(0, dt));
			enemy.devianceUp(dt);
		}
		else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			
			nyanCat.move(new Vector2(0, -dt));
			enemy.devianceDown(dt);
			
		}else if(Gdx.input.isKeyPressed(Keys.DOWN)){
			
		}else{
			
			enemy.stabilize(dt);
			
		}
		
	}

	private void checkBuffCollisions(Allied c) {
		
		if(c.rectangle.overlaps(nyanCat.rectangle)){
			
			if(c.tipo.equals("cat")){
				allies.removeValue(c, true);
				nyanCat.rainbows ++;
				levelManager.generateRainbow();
				
			}else if(c.tipo.equals("ikea")){
				
				if(c.collidable){
					allies.removeValue(c, true);
					
					if( SpriteManager.rainbows.size > 0)
						
						SpriteManager.rainbows.removeIndex(SpriteManager.rainbows.size-1);
					
					else{
						if (GameScreen.game.configurationManager.isSoundEnabled())
							GameScreen.wavSound.stop();
						es.mgj.nyancat.main.NyanCat.currentLevel = -1;
						GameScreen.game.setScreen(new GameOverScreen(GameScreen.game, "Game Over"));
					}
							
					nyanCat.rainbows --;
					
					rainbowIndex -- ;
				}
				
			}
		}
			
		if(c.rectangle.overlaps(enemy.rectangle)){
			
			if(c.tipo.equals("cat")){
				if(enemy.lives == 1){
					
					if (GameScreen.game.configurationManager.isSoundEnabled())
						GameScreen.wavSound.stop();
					
					if(GameScreen.tipo.equals("random"))
						
						GameScreen.game.setScreen(new GameOverScreen(GameScreen.game, "Victoria!!"));
					else{
						
						es.mgj.nyancat.main.NyanCat.currentLevel ++ ;
						
						if(!levelManager.readFile())
							return;
				
						GameScreen.game.setScreen(new GameScreen(GameScreen.game, "constructed"));
						GameScreen.game.paused = false;
					}
						
				}
				
				allies.removeValue(c, true);
				enemy.lives--;
				
			}else if(c.tipo.equals("muffincat")){
				
				this.enemy.pushBack();
				allies.removeValue(c, true);
			}
			
		}
			
	}
	
	public void render(SpriteBatch batch){
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		background.render(batch);
		
		for(Allied a : allies){
			
			if(a.stateTime >= 3f && a.tipo.equals("ikea") ){
				a.stop = true;
				a.collidable = true;
			}
			
			a.render(batch);
		}
		
		for(Rainbow r : rainbows){
			r.render(batch);
		}
		
		if(enemy.lives > 0){
			
			enemy.render(batch);
			countDown.render(batch);
		}else{
			enemy.rectangle.x = -100;
			enemy.rectangle.y = -100;
		}
			
		nyanCat.render(batch);
		
		levelManager.drawOnscreenText();
		
		batch.end();
	}

	@Override
	public boolean keyDown(int arg0) {
		
		return false;
	}


	@Override
	public boolean keyTyped(char arg0) {
		
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		
		if(arg0 == 131){
			GameScreen.game.setScreen(new InGameMenuScreen(GameScreen.game, this.gamescreen));
			
		}
		return false;
		
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		
		return false;
	}
}
