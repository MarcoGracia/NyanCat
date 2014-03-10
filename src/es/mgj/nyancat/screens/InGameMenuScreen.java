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

/**
 * Menú 'in-game'
 * Se muestra cuando el jugador pulsa la tecla ESCAPE y muestra algunas opciones de la partida
 * y la opción de salir del juego
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class InGameMenuScreen implements Screen {

	NyanCat game;
	GameScreen gameScreen;
	Stage stage;
	InGameMenuScreen in = this;
	
	public InGameMenuScreen(NyanCat game, GameScreen gameScreen) {
		this.game = game;
		this.gameScreen = gameScreen;
	}
	
	private void loadScreen() {
		
		stage = new Stage();
					
		Table table = new Table();
		table.setPosition(Constantes.SCREEN_WIDTH / 2.5f, Constantes.SCREEN_HEIGHT / 1.3f);
		
	    table.setFillParent(true);
	    table.setHeight(500);
	    stage.addActor(table);
		
		Label label = new Label("NyanCat", game.getSkin());
		table.addActor(label);
		
		TextButton buttonResume = new TextButton("Continuar", game.getSkin());
		buttonResume.setPosition(label.getOriginX(), label.getOriginY() - 120);
		buttonResume.setWidth(200);
		buttonResume.setHeight(40);
		buttonResume.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				game.resume();
				game.paused = false;
				dispose();
				
				game.setScreen(gameScreen);
				
			}
		});
		table.addActor(buttonResume);
		
		TextButton buttonMainMenu = new TextButton("Volver al Menu Principal", game.getSkin());
		buttonMainMenu.setPosition(label.getOriginX(), label.getOriginY() - 170);
		buttonMainMenu.setWidth(200);
		buttonMainMenu.setHeight(40);
		buttonMainMenu.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new MainMenuScreen(game));
				dispose();
				
			}
		});
		table.addActor(buttonMainMenu);
		
		TextButton buttonConfig = new TextButton("Configuration", game.getSkin());
		buttonConfig.setPosition(label.getOriginX(), label.getOriginY() - 220);
		buttonConfig.setWidth(200);
		buttonConfig.setHeight(40);
		buttonConfig.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new ConfigurationScreen(game, in));
				dispose();
				
			}
		});
		table.addActor(buttonConfig);
		
		TextButton buttonInstructions = new TextButton("Instrucciones", game.getSkin());
		buttonInstructions.setPosition(label.getOriginX(), label.getOriginY() - 270);
		buttonInstructions.setWidth(200);
		buttonInstructions.setHeight(40);
		buttonInstructions.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new InstructionsScreen(game, in));
				dispose();
				
			}
		});
		table.addActor(buttonInstructions);
		
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
				System.exit(0);
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
        
		
		stage.act(dt);
		stage.draw();
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {	
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {		
	}
}
