package es.mgj.nyancat.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import es.mgj.nyancat.util.Constantes;

public class Allied extends Character{

	float speed;
	public String tipo;
	public boolean collidable = true;
	
	public Allied(Animation animation, float x, float y, float speed) {
		
		super(animation, x, y);
		this.speed = speed;
		
	}
	
	@Override
	public void update(float dt){
		
		super.update(dt);
		position.add(new Vector2(dt*-speed, 0));
		
		rectangle.x = position.x;
		rectangle.y = position.y;
		
	}

}
