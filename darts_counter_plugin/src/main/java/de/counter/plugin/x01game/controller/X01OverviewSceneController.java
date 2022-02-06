package de.counter.plugin.x01game.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.x01game.data.X01GameObject;
import de.fx.spring.customisation.FxControlStylingService;
import de.fx.spring.resources.FXRManager;
import de.fx.spring.resources.FxSceneMover;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/x01game/overviewScene.fxml")
public class X01OverviewSceneController extends FxControlStylingService implements Initializable {
	
	private Button button;
	
	//For translation
	@FXML
	private Button back = new Button();
	@FXML
	private Label overviewHeader = new Label();
	@FXML
	private Label scoreLabel = new Label();
	@FXML
	private Label playerLabel = new Label();
	
	@FXML
	private Label score = new Label();
	
	@FXML
	private CheckBox doubleOut = new CheckBox();
	
	@FXML 
	private Label players = new Label();
	
	@Autowired
	private X01GameObject gameObject;
	
	@Autowired
	private FxSceneMover sceneMover;

	
	public void showPlayerScene(Event event) {
		sceneMover.moveToScene(event, X01PlayerSceneController.class);
	}

	public void start(Event event) {
		gameObject.setPlayer(gameObject.getPlayerList().get(0));
		if(doubleOut.isSelected()) {
			gameObject.setDoubleOut(true);
		}
		sceneMover.moveToScene(event,X01GameSceneController.class);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		score.setText(Integer.toString(gameObject.getScore()));
		players.setText(Integer.toString(gameObject.getPlayerList().size()));
		FXRManager.translateComponents(this.getClass(), back,overviewHeader,scoreLabel,playerLabel);
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
