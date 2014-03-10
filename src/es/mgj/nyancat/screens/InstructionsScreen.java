package es.mgj.nyancat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import es.mgj.nyancat.main.NyanCat;
import es.mgj.nyancat.util.Constantes;

public class InstructionsScreen implements Screen{
	
	NyanCat game;
	Stage stage;
	InGameMenuScreen inGameMenu;
	
	public InstructionsScreen(NyanCat game) {
		this.game = game;
	}
	
	public InstructionsScreen(NyanCat game, InGameMenuScreen in) {
		this.inGameMenu = in;
		this.game = game;
	}

	private void loadScreen() {
				
		
		stage = new Stage();
					
		Table table = new Table();
		table.setPosition(Constantes.SCREEN_WIDTH / 2.5f, Constantes.SCREEN_HEIGHT / 1.3f);
		
	    table.setFillParent(true);
	    table.setHeight(500);
	    stage.addActor(table);
	    
		Label label = new Label("Instrucciones", game.getSkin());
		table.addActor(label);
		
		Label label1 = new Label("NyanCat es un juego rapido, de tension y habilidad."
				+ " El objetivo del juego es destruir a tu perseguidor "
				+ "\ny acabar la partida con la mayor cantidad de arcoiris posible. Los gato-donut son beneficiosos para"
				+ "\nti y malos para tu perseguidor, si cazas un gatodonut te dara un arcoiris, otorgandote"
				+ "\nuna vida y un punto, los arcoiris te salvarán de las garras del peseguidor y evitaran"
				+ "\nque te pierdas entre los miles de muebles de ikea, pero ten en cuenta que a mas arcoiris"
				+ "\nmas facil sera para tu perseguidor arrebatartelos.\n"
				+ "\nEn cambio para el malvado NyanDog cada gato-donut es un golpe, deberas decidir si necesitas"
				+ "\nuna vida o si quieres golpear a NynDog. Y recuerda, el limite es la pantalla asi que calcula"
				+ "\nbien tus movimientos. Los gato-donut son un bien escaso!", game.getSkin());
		
		label1.setPosition(label.getOriginX()-250, label.getOriginY() -250);
		table.addActor(label1);
		
		// Botón
		TextButton buttonQuit = new TextButton("Salir", game.getSkin());
		buttonQuit.setPosition(label.getOriginX(), label.getOriginY() - 320);
		buttonQuit.setWidth(200);
		buttonQuit.setHeight(40);
		buttonQuit.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				dispose();
				if(inGameMenu == null)
					game.setScreen(new MainMenuScreen(game));
				else
				game.setScreen(inGameMenu);
				
			}
		});
		table.addActor(buttonQuit);
		
		
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {

		loadScreen();
	}
	
	@Override
	public void render(float dt) {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
		// Pinta el menú
		stage.act(dt);
		stage.draw();
	}
	
	@Override
	public void dispose() {

		stage.dispose();
	}

	@Override
	public void hide() {

		
	}

	@Override
	public void pause() {

		
	}

	

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
