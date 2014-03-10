package es.mgj.nyancat.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import es.mgj.nyancat.managers.ResourceManager;
import es.mgj.nyancat.util.Constantes;

public class NyanCat extends Character {

	public static final float SPEED = 200f;
	public int rainbows;
	public Enemy enemy;
	
	public NyanCat(Animation animation, float x, float y, float speed) {
		
		super(animation, x, y);
		
		rectangle.x = position.x;
		rectangle.y = position.y;
		
		
		
		this.speed = SPEED;
		
	}
	
	public void setEnemy(Enemy enemy){
		this.enemy = enemy;
	}
	
	public void move(Vector2 posicion){
		
		posicion.scl(SPEED);
		position.add(posicion);
		
		rectangle.x = position.x;
		rectangle.y = position.y;
		
		if(position.x + 35 >= Constantes.SCREEN_WIDTH)
			position.x = Constantes.SCREEN_WIDTH - 35;
		
		if(position.x <= 0)
			position.x = 0;
		
		if(position.y +35 >= Constantes.SCREEN_HEIGHT)
			position.y = Constantes.SCREEN_HEIGHT -35;
		
		if(position.y <= 0)
			position.y = 0;
		
	}
	

}
