package de.counter.plugin.menu.controller;


import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.cricket.controller.CricketPlayerSceneController;
import de.counter.plugin.language.LanguageSceneController;
import de.counter.plugin.x01game.controller.X01SceneController;

import de.fx.spring.customisation.FxAlert;
import de.fx.spring.customisation.FxControlStylingService;
import de.fx.spring.resources.FXRManager;
import de.fx.spring.resources.FxSceneMover;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Alert.AlertType;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/menu/menuScene.fxml")
public class MenuSceneController extends FxControlStylingService implements Initializable {

	private Button button;

	@FXML
	private Button setting = new Button();

	@FXML
	private Label menuHeader = new Label();

	@Autowired
	private FxSceneMover sceneMover;


	public void showX01Scene(Event event) {
		sceneMover.moveToScene(event, X01SceneController.class);
	}

	public void showPlayerScene(Event event) {
		sceneMover.moveToScene(event, CricketPlayerSceneController.class);
	}
	
	public void showLanguageScene(Event event) {
		sceneMover.moveToScene(event, LanguageSceneController.class);
	}

	public void showComingSoonAlert() {
		FxAlert.setAlert(AlertType.INFORMATION, "This Mode coming soon!!!");
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
		Image img = new Image("/images/setting_image.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(20);
		view.setPreserveRatio(true);
		setting.setGraphic(view);
		FXRManager.translateComponents(this.getClass(), menuHeader);
	}

}
