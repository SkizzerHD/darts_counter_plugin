package de.counter.plugin.launch;
import org.springframework.context.annotation.ComponentScan;

import de.counter.plugin.language.LanguageSceneController;
import de.fx.spring.exception.NoSourceClassFoundException;
import de.fx.spring.exception.NoSourceControllerClassFoundException;
import de.fx.spring.launch.ApplicationConfiguration;
import de.fx.spring.launch.JavaFxApplicationLauncher;
import de.fx.spring.resources.FXRManager;


@ComponentScan(basePackages = {"de.counter.plugin.menu.controller",
"de.counter.plugin.game","de.counter.plugin.x01game.controller",
"de.counter.plugin.x01game.data","de.counter.plugin.cricket.controller",
"de.counter.plugin.cricket.data","de.counter.plugin.language"})
public class ApplicationLaunch extends JavaFxApplicationLauncher {
	
	public static void main(String[] args)  {
		
		//set the translation-file 
		FXRManager.setTranslationFile("german_translation.txt");
		
		setSourceClass(ApplicationLaunch.class);
		setSourceController(LanguageSceneController.class);
		try {
			launch(ApplicationConfiguration.class,args);
		} catch (NoSourceClassFoundException | NoSourceControllerClassFoundException e) {
			e.printStackTrace();
		}
	}

}
