package es.mgj.nyancat.main;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.db4o.Db4oEmbedded;

import es.mgj.nyancat.managers.ConfigurationManager;
import es.mgj.nyancat.screens.MainMenuScreen;
import es.mgj.nyancat.util.Constantes;
import es.mgj.nyancat.util.Util;

public class NyanCat extends Game{
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public BitmapFont font;
	public static int currentLevel = 1;
	
	private Skin skin;
	
	public ConfigurationManager configurationManager;
	
	public int score;
	public boolean paused;
	
	@Override
	public void create() {
		
		configurationManager = new ConfigurationManager();
		
		Util.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), Constantes.DATABASE_FILENAME);
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);
		camera.update();
		setScreen (new MainMenuScreen(this)); 
		
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		font.dispose();
	}
	
	public Skin getSkin() {
		
        if(skin == null ) {
            skin = new Skin(Gdx.files.internal("menus/uiskin.json"));
        }
        return skin;
    }	

}
