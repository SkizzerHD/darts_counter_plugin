package de.counter.plugin.language;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.menu.controller.MenuSceneController;
import de.fx.spring.customisation.FxAlert;
import de.fx.spring.customisation.FxControlStylingService;
import de.fx.spring.resources.FXRManager;
import de.fx.spring.resources.FxSceneMover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/language/languageScene.fxml")
public class LanguageSceneController extends FxControlStylingService implements Initializable  {
	
	@FXML
	private ComboBox<Language> comboBox = new ComboBox<>();
	
	@Autowired
	private FxSceneMover sceneMover;
	
	private Button button;
	
	public void showMenuScene(Event event) {
		System.out.println(comboBox.getSelectionModel().getSelectedItem());
		if(comboBox.getSelectionModel().getSelectedItem() == null) {
			FxAlert.setAlert(AlertType.ERROR,"Please choose a language\n"
											+ "-----------------------------\n"
											+ "Bitte wähle eine Sprache");
		}else {
			if(comboBox.getSelectionModel().getSelectedItem().equals(Language.ENGLISH)) {
				FXRManager.setTranslationFile("english_translation.txt");
			}else if(comboBox.getSelectionModel().getSelectedItem().equals(Language.GERMAN)) {
				FXRManager.setTranslationFile("german_translation.txt");
			}
			sceneMover.moveToScene(event, MenuSceneController.class);
		}
	}

	@Override
	public void setHoveringStyle() {
		setControlObject(button);
		setStyle(";-fx-background-color: #FFFAF0");
	}

	@Override
	public void setControlActiveStyle() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Language>list = FXCollections.observableArrayList();
		list.setAll(Language.ENGLISH, Language.GERMAN);
		comboBox.setItems(list);
	}

}
