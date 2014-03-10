package es.mgj.nyancat.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import es.mgj.nyancat.managers.ResourceManager;
import es.mgj.nyancat.managers.SpriteManager;
import es.mgj.nyancat.screens.GameOverScreen;
import es.mgj.nyancat.screens.GameScreen;
import es.mgj.nyancat.screens.MainMenuScreen;
import es.mgj.nyancat.util.Constantes;

public class Enemy extends Character{

	private NyanCat nyanCat;
	
	public int lives;
	
	private Array<Rainbow> rainbows;
	
	private float stepStart;
	private float stepCharge;
	private float stepEnd;
	private float stepTime;
	private boolean charging;
	private boolean chargeHit;
	private float stepEndCharge;
	
	private Allied countDown;
	
	
	public Enemy(Animation animation, float x, float y, float speed) {
		super(animation, x, y);
		
		this.lives = 20;
		stepTime = 0;
		stepEnd = 8f;
		stepStart = 5f;
		stepCharge = 5.3f;
		stepEndCharge = 6.5f;
		speed = 50;
	}
	
	public void setNyancat(NyanCat nyanCat){
		this.nyanCat = nyanCat;
	}
	
	public void charge(){
		
	}
	
	@Override
	public void update(float dt, NyanCat nyanCat){
		
		super.update(dt, nyanCat);
		
		stepTime += dt;
		
		if (stepTime >= stepStart) {
				
			if (stepTime >= stepCharge && stepTime <= stepEndCharge){
				
				if(!chargeHit)
					position.add(new Vector2(dt * 500, 0));
				else
					position.add(new Vector2(dt * -150, 0));
			}else
				position.add(new Vector2(dt * -150, 0));
				
			charging = true;
			
		}
		
		if(stepTime >= stepEnd){
			
			this.speed = 50;
			stepTime = 0;
			charging = false;
			
		}
		
		this.chargeHit = false;
		
		if(!charging){
			position.add(new Vector2(dt * speed, 0));
		}
		
		
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
				
				if(charging){
					chargeHit = true;
					
				}
				
				this.position.add(new Vector2(dt* - 5000, 0));
				this.rainbows.removeValue(r, true);
				
			}
				
		}
		
		this.countDown.position.x = this.position.x;
		this.countDown.position.y = this.position.y + 50;
		
	}
	
	public void sprint(float dt){
		
		if(charging)
			return;
		
		position.add(new Vector2(dt * NyanCat.SPEED/2, 0));
		
		rectangle.x = position.x;
		rectangle.y = position.y;
	}
	
	public void devianceUp(float dt){
		
		if(charging)
			return;
		
		position.add(new Vector2(0, dt * NyanCat.SPEED* 0.9f ));
		
		rectangle.x = position.x;
		rectangle.y = position.y;
	}
	
	public void devianceDown(float dt){
		
		if(charging)
			return;
		
		position.add(new Vector2(0, dt * - NyanCat.SPEED *0.9f ));
		
		rectangle.x = position.x;
		rectangle.y = position.y;
	}

	public void stabilize(float dt) {
		
		if(charging)
			return;
		
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

	public void pushBack() {
		this.position.x = 0;
		
	}
	
	public void setCountDown(Allied countDown){
		this.countDown = countDown;
	}

}
