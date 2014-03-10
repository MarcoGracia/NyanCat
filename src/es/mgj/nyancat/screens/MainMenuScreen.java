package es.mgj.nyancat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import es.mgj.nyancat.main.NyanCat;
import es.mgj.nyancat.util.Constantes;

public class MainMenuScreen implements Screen{
	
	NyanCat game;
	Stage stage;
	
	public MainMenuScreen(NyanCat game) {
		this.game = game;
		game.paused = false;
	}
	
	private void loadScreen() {
		
		if(GameScreen.wavSound != null)
			GameScreen.wavSound.stop();
				
		stage = new Stage();
					
		Table table = new Table();
		table.setPosition(Constantes.SCREEN_WIDTH / 2.5f, Constantes.SCREEN_HEIGHT / 1.3f);
		
	    table.setFillParent(true);
	    table.setHeight(500);
	    stage.addActor(table);
	    
		Label label = new Label("NYANCAT GAME OF CRAZYNESS", game.getSkin());
		table.addActor(label);
		
		TextButton buttonPlay = new TextButton("Random Nyancat", game.getSkin());
		buttonPlay.setPosition(label.getOriginX(), label.getOriginY() - 120);
		buttonPlay.setWidth(200);
		buttonPlay.setHeight(40);
		buttonPlay.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				game.setScreen(new GameScreen(game, "random"));
				dispose();
			}
		});
		table.addActor(buttonPlay);
		
		// Botón
		TextButton buttonHistory = new TextButton("Constructed Nyancat", game.getSkin());
		buttonHistory.setPosition(label.getOriginX(), label.getOriginY() - 170);
		buttonHistory.setWidth(200);
		buttonHistory.setHeight(40);
		buttonHistory.addListener(new InputListener() {
			
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				NyanCat.currentLevel = 1;
				game.setScreen(new GameScreen(game, "constructed"));
				dispose();
				
			}
		});
		table.addActor(buttonHistory);
		
		// Botón
		TextButton buttonConfig = new TextButton("Configuration", game.getSkin());
		buttonConfig.setPosition(label.getOriginX(), label.getOriginY() - 220);
		buttonConfig.setWidth(200);
		buttonConfig.setHeight(40);
		buttonConfig.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new ConfigurationScreen(game));
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
				game.setScreen(new InstructionsScreen(game));
				dispose();
				
			}
		});
		table.addActor(buttonInstructions);
		
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
				
				game.dispose();
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

		Gdx.gl.glClearColor(5, 15, 0.2f, 1);
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
