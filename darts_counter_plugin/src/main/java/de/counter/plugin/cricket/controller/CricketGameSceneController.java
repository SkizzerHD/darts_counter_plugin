package de.counter.plugin.cricket.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.cricket.data.CricketGameObject;
import de.counter.plugin.cricket.data.CricketStatus;
import de.counter.plugin.menu.controller.MenuSceneController;
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
@FxmlView("/cricket/gameScene.fxml")
public class CricketGameSceneController extends FxControlStylingService implements Initializable{

	@FXML
	private Label player = new Label();
	@FXML
	private Button back = new Button();
	@FXML
	private Label playerLabel = new Label();
	@FXML
	private Label hit1 = new Label();
	@FXML
	private Label hit2 = new Label();
	@FXML
	private Label hit3 = new Label();
	@FXML
	private Label label15 = new Label();
	@FXML
	private Label label16 = new Label();
	@FXML
	private Label label17 = new Label();
	@FXML
	private Label label18 = new Label();
	@FXML
	private Label label19 = new Label();
	@FXML
	private Label label20 = new Label();
	@FXML
	private Label labelBull = new Label();
	@FXML 
	private Tooltip statD = new Tooltip("Click to activate");
	@FXML 
	private Tooltip statT = new Tooltip("Click to activate");
	@FXML
	private Button doubleButton = new Button();
	@FXML 
	private Button tripleButton = new Button();
	@FXML
	private Button next = new Button();
	@FXML
	private Button miss = new Button();

	private Label[] hitLabels;

	@Autowired
	private CricketGameObject gameObject;
	
	@Autowired
	private FxSceneMover sceneMover;

	private Button button;

	private int hits;
	
	private String text;
	
	private boolean fail;

	private CricketStatus status;

	public void showMenu(Event event) {
		sceneMover.moveToScene(event, MenuSceneController.class);
		
	}

	//For status style and tooltip
	public void setStatus(Event event) {
		if(hits < 3) {
			deactivateControlObject(event);
			button = (Button)event.getSource();
			if(button.getId().equals("doubleButton")) {
				if(status != CricketStatus.DOUBLE) {
					status = CricketStatus.DOUBLE;
					activateControlObject(event);
					//For Tooltip
					statD.setText("Click to deactivate");
					statT.setText("Click to activate");
				}else {
					status = CricketStatus.SINGLE;
					deactivateControlObject(event);
					//For Tooltip
					statD.setText("Click to activate");
				}
			}else if(button.getId().equals("tripleButton")) {
				if(status != CricketStatus.TRIPLE) {
					status = CricketStatus.TRIPLE;
					activateControlObject(event);
					//For Tooltip
					statT.setText("Click to deactivate");
					statD.setText("Click to activate");
				}else {
					status = CricketStatus.SINGLE;
					deactivateControlObject(event);
					//For Tooltip
					statT.setText("Click to activate");
				}
			}
		}
	}

	public void setHit(Event event) {
		if(hits < 3) {
			button = (Button) event.getSource();


			gameObject.getPlayer().getNumbers().stream().forEach(n->{
				if(n.getNumber().equals(button.getText())) {
					if(n.getHits() < 3) {
						if(status == CricketStatus.SINGLE) {
							n.setHits(n.getHits()+1);
						}else if(status == CricketStatus.DOUBLE) {
							if(n.getHits() <= 1) {
								n.setHits(n.getHits()+2);
							}else {
								status = CricketStatus.SINGLE;
								n.setHits(n.getHits()+1);
							}
						}else if(status == CricketStatus.TRIPLE && !n.getNumber().equals("BULL")) {
							if(n.getHits() == 0) {
								n.setHits(n.getHits()+3);
							}else if(n.getHits() == 1) {
								status = CricketStatus.DOUBLE;
								n.setHits(n.getHits()+2);
							}else if(n.getHits() == 2) {
								status = CricketStatus.SINGLE;
								n.setHits(n.getHits()+1);
							}
						}else if(status == CricketStatus.TRIPLE && n.getNumber().equals("BULL")) {
							fail = true;
						}
						if(!fail) {
							hits++;
							switch (hits) {
							case 1: setHitLabel(hit1, n.getNumber());
							break;
							case 2: setHitLabel(hit2, n.getNumber());
							break;
							case 3: setHitLabel(hit3, n.getNumber());
							}
							if(n.getHits() == 3) {
								n.setFinished(true);
							}
						}
					}else {
						if(!fail) {
							hits++;
							status = CricketStatus.SINGLE;
							switch (hits) {
							case 1: setHitLabel(hit1, "0");
							break;
							case 2: setHitLabel(hit2, "0");
							break;
							case 3: setHitLabel(hit3, "0");
							}
						}
					}

				}
			});

			deactivateControlObject(doubleButton);
			deactivateControlObject(tripleButton);

			status = CricketStatus.SINGLE;
			fail = false;

			//For Tooltip and controls
			statD.setText("Click to activate");
			statT.setText("Click to activate");

			// new ag for playerstats
			setPlayerStats();

			if(hits == 3) {
				activateControlObject(next);
			}
			
			gameObject.checkEnd(event);
		}
	}
	
	public void reset() {
		if(hits > 0 ) {
			switch(hits) {
			case 1 : 
				text = hit1.getText();
				hit1.setText("-");
				break;
			case 2:
				text = hit2.getText();
				hit2.setText("-");
				break;
			case 3:
				text = hit3.getText();
				hit3.setText("-");
				deactivateControlObject(next);
				break;
			}
			hits--;
			if(text.substring(0,1).equals("D")) {
				gameObject.getPlayer().getNumbers().stream().
				forEach(n->{
					if(n.getNumber().equals(text.substring(2,text.length()))) {
						if(n.isFinished()) {
							n.setFinished(false);
						}
						n.setHits(n.getHits()-2);
					}
				});
			}else if(text.substring(0,1).equals("T")) {
				gameObject.getPlayer().getNumbers().stream().
				forEach(n->{
					if(n.getNumber().equals(text.substring(2,text.length()))) {
						if(n.isFinished()) {
							n.setFinished(false);
						}
						n.setHits(n.getHits()-3);
					}
				});
			}else {
				gameObject.getPlayer().getNumbers().stream().
				forEach(n->{
					if(n.getNumber().equals(text)) {
						if(n.isFinished()) {
							n.setFinished(false);
						}
						n.setHits(n.getHits()-1);
					}
				});
			}
			setPlayerStats();
		}
	}

	public void next() {
		if(gameObject.getPlayer().getId() == gameObject.getPlayers().size()) {
			gameObject.setPlayer(gameObject.getPlayers().get(0));
		}else {
			gameObject.setPlayer(gameObject.getPlayers().get(gameObject.getPlayer().getId()));
		}
		hits = 0;
		setPlayerStats();
		deactivateControlObject(next);
		hit1.setText("-");
		hit2.setText("-");
		hit3.setText("-");
	}

	public void miss() {
		if(hits < 3) {
			hits++;
			if(hits == 1) {
				setHitLabel(hit1, "0");
			}else if(hits == 2) {
				setHitLabel(hit2, "0");
			}
			if(hits == 3) {
				setHitLabel(hit3, "0");
				activateControlObject(next);
			}
		}
	}

	public void setPlayerStats() {
		player.setText(Integer.toString(gameObject.getPlayer().getId()));
		//for all hitLabels
		for(int i=0; i<gameObject.getPlayer().getNumbers().size();i++) {
			switch(gameObject.getPlayer().getNumbers().get(i).getHits()) {
			case 0: hitLabels[i].setText("");
			break;
			case 1: hitLabels[i].setText("I");
			break;
			case 2: hitLabels[i].setText("I  I");
			break;
			case 3:	hitLabels[i].setText("I  I  I");
			break;
			}
		}
	}

	private void setHitLabel(Label label,String text) {
		if(status == CricketStatus.SINGLE) {
			label.setText(text);
		}else if(status == CricketStatus.DOUBLE) {
			label.setText("D-"+text);
		}else if(status == CricketStatus.TRIPLE) {
			label.setText("T-"+text);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hitLabels = new Label[] {label15,label16,label17,label18,label19,label20,labelBull};
		hits = 0;
		status = CricketStatus.SINGLE;
		doubleButton.setTooltip(statD);
		tripleButton.setTooltip(statT);
		setPlayerStats();
		FXRManager.translateComponents(getClass(), back,playerLabel,next,miss,doubleButton,tripleButton);
	}

	@Override
	public void setHoveringStyle() {
		setControlObject(button);
		setStyle("-fx-background-color: #FFFAF0");
	}

	@Override
	public void setControlActiveStyle() {
		setControlObject(button);
		setStyle("-fx-background-color: #7FFF00");
	}
}
