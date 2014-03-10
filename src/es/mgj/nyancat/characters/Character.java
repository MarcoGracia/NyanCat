package es.mgj.nyancat.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Character {
	
	Vector2 position;
	float speed;
	public Animation animation;
	TextureRegion currentFrame;
	public float stateTime;
	public Rectangle rectangle;
	public boolean stop;
	
	public Character(Animation animation, float x, float y){
		
		position = new Vector2(x,y);
		this.animation = animation;
		speed = 50f;
		rectangle = new Rectangle(position.x, position.y, 
				animation.getKeyFrame(6).getRegionWidth(), 
				animation.getKeyFrame(6).getRegionHeight());
		
	}
	
	public Character(TextureRegion texture, float x, float y){
		
		currentFrame = texture;
		position = new Vector2(x,y);
		speed = 50f;
		
		rectangle = new Rectangle(position.x, position.y, 
				currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
	}
	
	public void render(SpriteBatch batch){
		
		batch.draw(currentFrame, position.x, position.y);
	}
	
	public void update(float dt){
		
		if(stop){
			
			return;
		}
			
		stateTime += dt;
		
		
		
		if(animation != null){
			
			currentFrame = animation.getKeyFrame(stateTime, true);
		}
			
			
	}
	
	public void update(float dt, NyanCat nyanCat){
		
		stateTime += dt;
		
		if(animation != null)
			currentFrame = animation.getKeyFrame(stateTime, true);
		
	}
	
}
