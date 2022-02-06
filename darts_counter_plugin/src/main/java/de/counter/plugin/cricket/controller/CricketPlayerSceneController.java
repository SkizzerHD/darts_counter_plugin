package de.counter.plugin.cricket.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.cricket.data.CricketGameObject;
import de.counter.plugin.menu.controller.MenuSceneController;
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
@FxmlView("/cricket/playerScene.fxml")
public class CricketPlayerSceneController extends FxControlStylingService implements Initializable {
	
	private Button button;
	
	@FXML
	private Button back = new Button();
	
	@FXML
	private Label playerHeader = new Label();
	
	@Autowired
	private CricketGameObject gameObject;
	
	@Autowired
	private FxSceneMover sceneMover;

	public void setPlayersAndShowGameScene(Event event) {
		button = (Button)event.getSource();
		gameObject.fillPlayerList(Integer.parseInt(button.getText()));
		sceneMover.moveToScene(event, CricketOverviewSceneController.class);
	}
	
	public void showMenuScene(Event event) {
		sceneMover.moveToScene(event, MenuSceneController.class);
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
	FXRManager.translateComponents(getClass(), back,playerHeader);
		
	}

}
