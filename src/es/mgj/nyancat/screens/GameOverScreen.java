package es.mgj.nyancat.screens;

import java.util.ArrayList;

import java.util.Collections;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.db4o.ObjectSet;

import es.mgj.nyancat.base.Score;
import es.mgj.nyancat.main.NyanCat;
import es.mgj.nyancat.managers.SpriteManager;
import es.mgj.nyancat.util.Constantes;
import es.mgj.nyancat.util.Util;

public class GameOverScreen implements Screen {

	NyanCat game;
	Stage stage;
	String result;
	
	private boolean done;
	
	public GameOverScreen(NyanCat game, String result) {
		this.result = result;
		this.game = game;
		
		done = false;
	}
	
	private void loadScores(Table table, Stage stage, float x, float y) {
		
		ObjectSet<Score> scores = Util.db.query(Score.class);
		
		ArrayList<Score> temp = new ArrayList<Score>();
		
		for(Score s : scores){
			temp.add(s);
		}
		
		Collections.sort(temp);
		
		Label labelList = null;
		
		for (int i = 0 ; i < 10 ; i++) {
			
			labelList = new Label(temp.get(i).getName()+ " - " + temp.get(i).getRainbows(), game.getSkin());
			labelList.setPosition(x, y);
			table.addActor(labelList);
			y -= 20;
			
		}
	}
	
	private void loadScreen() {
		
		
		stage = new Stage();
					
		
		final Table table = new Table();
		table.setPosition(Constantes.SCREEN_WIDTH / 2.5f, Constantes.SCREEN_HEIGHT / 1.3f);
		
	    table.setFillParent(true);
	    table.setHeight(500);
	    stage.addActor(table);
	    
	    final Label label0 = new Label(result, game.getSkin());
		table.addActor(label0);
		
		final Label label = new Label("Mejores puntuaciones NyanCat", game.getSkin());
		label.setPosition(label.getOriginX(), -20);
		table.addActor(label);
		
		loadScores(table, stage, label.getOriginX(), -50);
		
		Label labelScore = new Label("Tu puntuacion: " + SpriteManager.rainbows.size, game.getSkin());
		labelScore.setPosition(label.getOriginX(), label.getOriginY() - 300);
		table.addActor(labelScore);
		
		
		if (done) {
			TextButton buttonQuit = new TextButton("Volver", game.getSkin());
			buttonQuit.setPosition(label.getOriginX(), label.getOriginY() - 350);
			buttonQuit.setWidth(200);
			buttonQuit.setHeight(40);
			buttonQuit.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					game.setScreen(new MainMenuScreen(game));
				}
			});
			table.addActor(buttonQuit);
		}
		else {
		
			final TextField textName = new TextField("Introduce tu nombre", game.getSkin());
			textName.setPosition(label.getOriginX(), label.getOriginY() - 250);
			textName.setWidth(200);
			textName.setHeight(40);
			table.addActor(textName);
			
			TextButton buttonQuit = new TextButton("Ok", game.getSkin());
			buttonQuit.setPosition(label.getOriginX(), label.getOriginY() - 350);
			buttonQuit.setWidth(200);
			buttonQuit.setHeight(40);
			
			buttonQuit.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					Score s = new Score();
					
					s.setName(textName.getText());
					s.setRainbows(SpriteManager.rainbows.size);
					
					Util.db.store(s);
					Util.db.commit();
					
					stage.clear();
					done = true;
					loadScreen();
				}
			});
			table.addActor(buttonQuit);
		}
		
		
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
