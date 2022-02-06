package de.counter.plugin.x01game.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.menu.controller.MenuSceneController;
import de.counter.plugin.x01game.data.X01GameObject;
import de.fx.spring.customisation.FxControlStylingService;
import de.fx.spring.resources.FXRManager;
import de.fx.spring.resources.FxSceneMover;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/x01game/X01Scene.fxml")
public class X01SceneController extends FxControlStylingService implements Initializable {

	private Button button;
	
	//Created for translation
	@FXML
	private Button back = new Button();
	@FXML 
	private Label X01header = new Label();
	
	@Autowired
	private X01GameObject gameObject;
	
	@Autowired
	private FxSceneMover sceneMover;
	
	public void showMenuScene(Event event){
		sceneMover.moveToScene(event, MenuSceneController.class);
	}
	
	public void setScoreAndShowPlayerScene(Event event){
		button = (Button) event.getSource();
		gameObject.setScore(Integer.parseInt(button.getText()));
		sceneMover.moveToScene(event, X01PlayerSceneController.class);
	}

	@Override
	public void setHoveringStyle() {
		setControlObject(button);
		setStyle("-fx-background-color: #FFFAF0");
	}

	@Override
	public void setControlActiveStyle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FXRManager.translateComponents(this.getClass(), back, X01header);
	}

}
