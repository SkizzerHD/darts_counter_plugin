package de.counter.plugin.x01game.controller;
import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.menu.controller.MenuSceneController;
import de.counter.plugin.x01game.data.X01Player;

import de.counter.plugin.x01game.data.X01GameObject;
import de.fx.spring.customisation.FxControlStylingService;
import de.fx.spring.resources.FXRManager;
import de.fx.spring.resources.FxSceneMover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/x01game/statisticsScene.fxml")
public class X01StatisticsSceneController extends FxControlStylingService implements Initializable{

	@FXML 
	private Button end = new Button();
	@FXML
	private Label statsHeader = new Label();
	@FXML
	private TableView<X01Player> tableView;
	@FXML
	private TableColumn<X01Player, Integer>id ;
	@FXML
	private TableColumn<X01Player, String>name;
	@FXML
	private TableColumn<X01Player, Integer>restScore;
	@FXML
	private TableColumn<X01Player, Double>avg;
	@FXML
	private TableColumn<X01Player, Integer>thrownDarts;

	@Autowired
	private X01GameObject gameObject;
	
	@Autowired
	private FxSceneMover sceneMover;
	
	private Button button;
	
	private ObservableList<X01Player>list;
	
	//all elements of the table are initialized and the table is filled
	public void fillTable() {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		restScore.setCellValueFactory(new PropertyValueFactory<>("score"));
		avg.setCellValueFactory(new PropertyValueFactory<>("avg"));
		thrownDarts.setCellValueFactory(new PropertyValueFactory<>("ammountThrows"));
		list.setAll(gameObject.getPlayerList());
		tableView.setItems(list);
	}
	
	public void showMenuScene(Event event) {
		sceneMover.moveToScene(event, MenuSceneController.class);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FXRManager.translateComponents(getClass(),
				statsHeader,end);
		list = FXCollections.observableArrayList();
		fillTable();	
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
