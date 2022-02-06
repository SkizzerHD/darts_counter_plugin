package de.counter.plugin.cricket.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.counter.plugin.launch.GameObject;
import de.counter.plugin.menu.controller.MenuSceneController;
import de.fx.spring.customisation.FxAlert;
import de.fx.spring.resources.FxSceneMover;
import javafx.event.Event;
import javafx.scene.control.Alert.AlertType;
import lombok.Data;

@Data
@Component
public class CricketGameObject implements GameObject{
	
	private CricketPlayer player;
	
	private List<CricketPlayer>players;
	
	@Autowired
	private FxSceneMover sceneMover;

	
	public void fillPlayerList(int amountPlayers) {
		players = new ArrayList<>();
		for(int i=0; i<amountPlayers;i++) {
			players.add(new CricketPlayer(i+1));
		}
		player = players.get(0);
	}
	
	public Object checkEnd(Event event) {
		int numbersFinished = 0;
		for(CricketNumber number : player.getNumbers()) {
			if(number.isFinished()) {
				numbersFinished++;
			}
		}
		if(numbersFinished == 7) {
			FxAlert.setAlert(AlertType.INFORMATION,player.getName()+" won");
			sceneMover.moveToScene(event, MenuSceneController.class);
		}
		return null;
	}

}
