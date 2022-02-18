package de.counter.plugin.launch;
import org.springframework.context.annotation.ComponentScan;
import de.counter.plugin.language.LanguageSceneController;
import de.fx.spring.exception.NoSourceClassFoundException;
import de.fx.spring.exception.NoSourceControllerClassFoundException;
import de.fx.spring.launch.JavaFxApplicationLauncher;


@ComponentScan(basePackages = {"de.counter.plugin.menu.controller",
"de.counter.plugin.game","de.counter.plugin.x01game.controller",
"de.counter.plugin.x01game.data","de.counter.plugin.cricket.controller",
"de.counter.plugin.cricket.data","de.counter.plugin.language","de.counter.plugin.data"})
public class ApplicationLauncher extends JavaFxApplicationLauncher {

	
	public static void main(String[] args)  {
		try {
			launch(ApplicationLauncher.class,LanguageSceneController.class,args);
		} catch (NoSourceClassFoundException | NoSourceControllerClassFoundException e) {
			e.printStackTrace();
		}
	}

}
