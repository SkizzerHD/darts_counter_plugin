package de.counter.plugin.x01game.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.menu.controller.MenuSceneController;
import de.counter.plugin.x01game.data.X01Status;
import de.counter.plugin.x01game.data.X01GameObject;
import de.fx.spring.customisation.FxControlStylingService;
import de.fx.spring.resources.FXRManager;
import de.fx.spring.resources.FxSceneMover;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import net.rgielen.fxweaver.core.FxmlView;


@Component
@FxmlView("/x01game/gameScene.fxml")
public class X01GameSceneController extends FxControlStylingService implements Initializable {
	
	//For translation
	@FXML
	private Button back = new Button();
	@FXML
	private Label playerLabel = new Label();

	//All FXML Scene Components
	@FXML
	private Label player = new Label();
	@FXML
	private Label score = new Label();
	@FXML
	private Label hit1 = new Label();
	@FXML
	private Label hit2 = new Label();
	@FXML
	private Label hit3 = new Label();
	@FXML
	private Label avg = new Label();
	@FXML
	private Button next = new Button();
	@FXML 
	private Tooltip statD = new Tooltip("Click to activate");
	@FXML 
	private Tooltip statT = new Tooltip("Click to activate");
	@FXML 
	private Button tripleButton = new Button();
	@FXML 
	private Button doubleButton = new Button();
	@FXML
	private Label doubleOutLabel = new Label();

	@Autowired
	private X01GameObject gameObject;
	
	@Autowired
	private FxSceneMover sceneMover;


	private Button button;

	private int hits;

	private X01Status status;

	public void showMenu(Event event) {
		sceneMover.moveToScene(event, MenuSceneController.class);
	}

	//get called when double or triple get pressed
	//control object gets activeStyle 
	public void setStatus(Event event) {
		if(hits < 3) {
			deactivateControlObject(event);
			button = (Button)event.getSource();

			if(button.getText().equals("DOUBLE")) {
				if(status != X01Status.DOUBLE) {
					status = X01Status.DOUBLE;
					activateControlObject(event);
					//For Tooltip
					statD.setText("Click to deactivate");
					statT.setText("Click to activate");
				}else {
					status = X01Status.SINGLE;
					deactivateControlObject(event);
					//For Tooltip
					statD.setText("Click to activate");
				}

			}else if(button.getText().equals("TRIPLE")) {
				if(status != X01Status.TRIPLE) {
					status = X01Status.TRIPLE;
					activateControlObject(event);
					//For Tooltip
					statT.setText("Click to deactivate");
					statD.setText("Click to activate");
				}else {
					status = X01Status.SINGLE;
					deactivateControlObject(event);
					//For Tooltip
					statT.setText("Click to activate");
				}
			}
		}
	}

	//get called by clicking score button
	public void setScore(Event event) {
		if(hits < 3) {
			button = (Button)event.getSource();

			//score gets calculated
			int scoring =0;
			if(status == X01Status.SINGLE) {
				scoring = Integer.parseInt(button.getText());	
			}else if(status == X01Status.DOUBLE) {
				scoring = Integer.parseInt(button.getText())*2;
			}else if(status == X01Status.TRIPLE) {
				scoring = Integer.parseInt(button.getText())*3;
			}

			//The score is deducted from the player score and is the new player score
			gameObject.getPlayer().setScore(gameObject.getPlayer().getScore()-scoring);

			//The respective thrown score is assigned to the labels
			hits++;
			switch(hits) {
			case 1: hit1.setText(Integer.toString(scoring));
			break;
			case 2:	hit2.setText(Integer.toString(scoring));
			break;
			case 3: hit3.setText(Integer.toString(scoring));
			break;
			}

			//The darts thrown are increased for the avg calculation
			gameObject.getPlayer().setAmmountThrows(
					gameObject.getPlayer().getAmmountThrows()+1);

			//Now are coming checks for gameEnd or busting

			//When the scoring is under 0 the bust method gets called
			if(gameObject.getPlayer().getScore() < 0) {
				bust();
			}
			
			//if score equals 1 you can't finish with double so you busted
			if(gameObject.getPlayer().getScore() == 1 && gameObject.isDoubleOut()) {
				bust();
			}

			//After each throw it is checked whether the game is over
			//It also gets checked if doubleOut ist true 
			if((boolean)gameObject.checkEnd(event)) {
				if(gameObject.isDoubleOut() && status == X01Status.SINGLE ||
						gameObject.isDoubleOut() && status == X01Status.TRIPLE) {
					bust();
				}else {
					sceneMover.moveToScene(event, X01StatisticsSceneController.class);
				}
			}

			//Calculations and new ad(display stats)
			//the new avg gets calculated
			gameObject.calculatePlayerAvg();
			//the labels for player attributes get updated
			setPlayerStats();

			//Double or Triple button get passive and status is Single
			deactivateControlObject(event);
			status = X01Status.SINGLE;

			//If it has been thrown three times, the next button is activated
			if(hits == 3) {
				activateControlObject(next);
			}
		}

		//For Tooltip
		statD.setText("Click to activate");
		statT.setText("Click to activate");
	}

	//get called by clicking next button
	public void next() {
		if(hits == 3) {
			hits = 0;
			hit1.setText("-");
			hit2.setText("-");
			hit3.setText("-");
			//Next player gets choosen
			do {
				if(gameObject.getPlayer().getId() == gameObject.getPlayerList().size()) {
					gameObject.setPlayer(gameObject.getPlayerList().get(0));
				}else {
					gameObject.setPlayer(gameObject.getPlayerList().get(gameObject.getPlayer().getId()));	
				}
			}while(gameObject.getPlayer().getScore() == 0);
			setPlayerStats();
			deactivateControlObject(next);
		}
	}

	//reset the score from the last hit 
	public void reset() {
		int i = 0;
		if(hits > 0 && hits < 4) {
			switch(hits) {
			case 1: i = Integer.parseInt(hit1.getText());
			hit1.setText("-");
			break;
			case 2: i = Integer.parseInt(hit2.getText());
			hit2.setText("-");
			break;
			case 3: i = Integer.parseInt(hit3.getText());
			hit3.setText("-");
			break;
			}
			if(hits==3) {
				deactivateControlObject(next);
			}
			hits--;
			gameObject.getPlayer().setScore(gameObject.getPlayer().getScore()+i);

			//For avg calculation
			gameObject.getPlayer().setAmmountThrows(gameObject.getPlayer().getAmmountThrows()-1);
			if(gameObject.getPlayer().getAmmountThrows()>=1) {
				gameObject.calculatePlayerAvg();
			}else {
				gameObject.getPlayer().setAvg(0.00);
			}
			setPlayerStats();
		}
	}

	// reset to the old score of the player 
	//get called when score under 0
	private void bust() {
		int i =0;
		switch(hits) {
		case 1: i = Integer.parseInt(hit1.getText());
		break;
		case 2: i = Integer.parseInt(hit1.getText())+Integer.parseInt(hit2.getText());
		break;
		case 3: i = Integer.parseInt(hit1.getText())+Integer.parseInt(hit2.getText())
		+Integer.parseInt(hit3.getText());
		break;
		}
		//For avg calculation
		gameObject.getPlayer().setAmmountThrows(
				gameObject.getPlayer().getAmmountThrows()-hits+3);

		gameObject.getPlayer().setScore(gameObject.getPlayer().getScore()+i);
		hits = 3;
	}

	//the labels get the new Player Informations
	private void setPlayerStats() {
		player.setText(Integer.toString(gameObject.getPlayer().getId()));
		score.setText(Integer.toString(gameObject.getPlayer().getScore()));
		DecimalFormat f = new DecimalFormat("#0.0");
		avg.setText(f.format(gameObject.getPlayer().getAvg()));
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hits = 0;
		status = X01Status.SINGLE; 
		setPlayerStats();
		doubleButton.setTooltip(statD);
		tripleButton.setTooltip(statT);
		if(gameObject.isDoubleOut()) {
			doubleOutLabel.setText("YES");
		}
		FXRManager.translateComponents(this.getClass(), back,playerLabel);
	}

	@Override
	public void setHoveringStyle() {
		setControlObject(button);
		setStyle(";-fx-background-color: #FFFAF0");
	}

	@Override
	public void setControlActiveStyle() {
		setControlObject(button);
		setStyle(";-fx-background-color: #7FFF00");
	}


}
