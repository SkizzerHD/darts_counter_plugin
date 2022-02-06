package de.counter.plugin.cricket.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.cricket.data.CricketGameObject;
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
@FxmlView("/cricket/overviewScene.fxml")
public class CricketOverviewSceneController extends FxControlStylingService implements Initializable{

	private Button button;
	
	@FXML
	private Label playerLabel = new Label();
	@FXML
	private Button back = new Button();
	@FXML
	private Label overviewHeader = new Label();
	
	@FXML
	private Label player = new Label();
	
	@Autowired
	private FxSceneMover sceneMover;
	
	@Autowired
	private CricketGameObject gameObject;
	
	public void start(Event event) {
		sceneMover.moveToScene(event, CricketGameSceneController.class);
	}
	
	public void showPlayerScene(Event event) {
		sceneMover.moveToScene(event, CricketPlayerSceneController.class);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FXRManager.translateComponents(getClass(), back,overviewHeader,playerLabel);
		player.setText(Integer.toString(gameObject.getPlayers().size()));
		
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

}
