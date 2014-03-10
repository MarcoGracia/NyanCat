package es.mgj.nyancat.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import es.mgj.nyancat.managers.SpriteManager;
import es.mgj.nyancat.screens.GameOverScreen;
import es.mgj.nyancat.screens.GameScreen;
import es.mgj.nyancat.screens.MainMenuScreen;
import es.mgj.nyancat.util.Constantes;

public class Enemy extends Character{

	private NyanCat nyanCat;
	public int lives = 5;
	private Array<Rainbow> rainbows;
	
	public Enemy(Animation animation, float x, float y, float speed) {
		
		super(animation, x, y);
		this.lives = 5;
	}
	
	public void setNyancat(NyanCat nyanCat){
		this.nyanCat = nyanCat;
	}
	
	@Override
	public void update(float dt, NyanCat nyanCat){
		
		super.update(dt, nyanCat);
		
		position.add(new Vector2(dt * 50, 0));
		
		rectangle.x = position.x;
		rectangle.y = position.y;
		
		if(position.x + 58 > nyanCat.position.x)
			position.x = nyanCat.position.x-58;
		
		if(position.x + 35 >= Constantes.SCREEN_WIDTH)
			position.x = Constantes.SCREEN_WIDTH - 35;
		
		/*if(position.x <= 0)
			position.x = 0;*/
		
		if(position.y +35 >= Constantes.SCREEN_HEIGHT)
			position.y = Constantes.SCREEN_HEIGHT -35;
		
		if(position.y <= 0)
			position.y = 0;
		
		if(this.rectangle.overlaps(nyanCat.rectangle)){
			
			if (GameScreen.game.configurationManager.isSoundEnabled())
				GameScreen.wavSound.stop();
			
			es.mgj.nyancat.main.NyanCat.currentLevel = -1;
			GameScreen.game.setScreen(new GameOverScreen(GameScreen.game, "Game Over"));
			
		}
			
		
		for(Rainbow r : this.rainbows){
			
			if(this.rectangle.overlaps(r.rectangle)){

				this.nyanCat.rainbows--;
				
				SpriteManager.rainbowIndex--;
				
				this.position.add(new Vector2(dt* - 6000, 0));
				this.rainbows.removeValue(r, true);
				
			}
				
		}
		
	}
	
	public void sprint(float dt){
		
		position.add(new Vector2(dt * NyanCat.SPEED/2, 0));
		
		rectangle.x = position.x;
		rectangle.y = position.y;
	}
	
	public void devianceUp(float dt){
		
		position.add(new Vector2(0, dt * NyanCat.SPEED* 0.9f ));
		
		rectangle.x = position.x;
		rectangle.y = position.y;
	}
	
	public void devianceDown(float dt){
		
		position.add(new Vector2(0, dt * - NyanCat.SPEED *0.9f ));
		
		rectangle.x = position.x;
		rectangle.y = position.y;
	}

	public void stabilize(float dt) {
		
		if( this.position.y > nyanCat.position.y + 5){
			
			position.add(new Vector2(0, dt * - NyanCat.SPEED * 0.9f));
			
		}else if ( this.position.y < nyanCat.position.y - 5){
			
			position.add(new Vector2(0, dt * NyanCat.SPEED * 0.9f ));
			
		}
				
	}

	public Array<Rainbow> getRainbows() {
		return rainbows;
	}

	public void setRainbows(Array<Rainbow> rainbows) {
		this.rainbows = rainbows;
	}

}
