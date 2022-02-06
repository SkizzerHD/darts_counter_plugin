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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;


@Component
@FxmlView("/x01game/playerScene.fxml")
public class X01PlayerSceneController extends FxControlStylingService implements Initializable {
	
	private Button button;
	
	//For translation
	@FXML
	private Button back = new Button();
	@FXML
	private Label playerHeader = new Label();
	
	@FXML
	private AnchorPane root = new AnchorPane();

	@Autowired
	private X01GameObject gameObject;

	@Autowired
	private FxSceneMover sceneMover;
	
	public void showX01Scene(Event event) {
		sceneMover.moveToScene(event, X01SceneController.class);
	
	}
	
	public void setPlayerAmountAndShowGameScene(Event event) {
		button = (Button)event.getSource();
		gameObject.fillPlayerList(Integer.parseInt(button.getText()));
		sceneMover.moveToScene(event, X01OverviewSceneController.class);
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
		FXRManager.translateComponents(this.getClass(), back,playerHeader);
	}
}
