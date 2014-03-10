package es.mgj.nyancat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import es.mgj.nyancat.main.NyanCat;
import es.mgj.nyancat.managers.ResourceManager;
import es.mgj.nyancat.util.Constantes;

public class ConfigurationScreen implements Screen {

	NyanCat game;
	Stage stage;
	InGameMenuScreen inGameMenu;
	Preferences prefs;
	
	public ConfigurationScreen(NyanCat game) {
		
		this.game = game;
	}
	
	public ConfigurationScreen(NyanCat game, InGameMenuScreen inGameMenu) {
		this.inGameMenu = inGameMenu;
		this.game = game;
	}
	
	private void loadScreen() {
		
		stage = new Stage();
					
		Table table = new Table();
		table.setPosition(Constantes.SCREEN_WIDTH / 2.5f, Constantes.SCREEN_HEIGHT / 1.3f);
		
	    table.setFillParent(true);
	    table.setHeight(500);
	    stage.addActor(table);
		
		Label label = new Label("Configurar NyanCat", game.getSkin());
		table.addActor(label);
		
		final CheckBox checkSound = new CheckBox(" Sonido", game.getSkin());
		checkSound.setChecked(prefs.getBoolean("sound"));
		checkSound.setPosition(label.getOriginX(), label.getOriginY() - 40);
		checkSound.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				prefs.putBoolean("sound", checkSound.isChecked());
				
				if (game.configurationManager.isSoundEnabled()){
					if(GameScreen.wavSound != null)
						GameScreen.wavSound.resume();
					
				}else{
					if(GameScreen.wavSound != null)
						GameScreen.wavSound.pause();
				}
				
				
			}
		});
		table.addActor(checkSound);
		
		TextButton buttonMainMenu = new TextButton("Volver", game.getSkin());
		buttonMainMenu.setPosition(label.getOriginX(), label.getOriginY() - 220);
		buttonMainMenu.setWidth(200);
		buttonMainMenu.setHeight(40);
		buttonMainMenu.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				prefs.flush();
				dispose();
				if(inGameMenu == null)
					game.setScreen(new MainMenuScreen(game));
				else
					game.setScreen(inGameMenu);
			}
		});
		table.addActor(buttonMainMenu);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	/**
	 * Carga las preferencias del juego
	 */
	private void loadPreferences() {
		
		prefs = Gdx.app.getPreferences(Constantes.APP);
		
		if (!prefs.contains("sound"))
			prefs.putBoolean("sound", true);
	}
	
	@Override
	public void show() {
	
		loadPreferences();
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
	}

	@Override
	public void resume() {
	
		
	}
}
