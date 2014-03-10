package es.mgj.nyancat.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import es.mgj.nyancat.util.Constantes;

public class Rainbow extends Character{

	float speed;
	public int index;
	public NyanCat nyanCat;
	
	public Rainbow(Animation animation, float x, float y, float speed) {
		
		super(animation, x, y);
		this.speed = speed;
		
	}
	
	@Override
	public void update(float dt){
		
		super.update(dt);
		
		position.x = nyanCat.position.x - index * 6;
		position.y = nyanCat.position.y;
		
		rectangle.x = position.x;
		rectangle.y = position.y;
		
		if(position.x < 0)
			position.x = Constantes.SCREEN_WIDTH;
		
		
	}
	
	public void setNyanCat(NyanCat nyanCat2) {
		this.nyanCat = nyanCat2;
		
	}

}
