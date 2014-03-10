package es.mgj.nyancat.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import es.mgj.nyancat.util.Constantes;

public class ConfigurationManager {
	
	private Preferences prefs;

	public ConfigurationManager() {
		prefs = Gdx.app.getPreferences(Constantes.APP);
	}
	
	public boolean isSoundEnabled() {
		
		return prefs.getBoolean("sound");
	}
}
