package de.counter.plugin.cricket.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.counter.plugin.data.GameObject;
import de.counter.plugin.menu.controller.MenuSceneController;
import de.fx.spring.customisation.FxInformationScene;
import de.fx.spring.customisation.InfoType;
import de.fx.spring.resources.FXRManager;
import de.fx.spring.resources.FxSceneMover;
import javafx.event.Event;
import javafx.scene.control.Label;
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
			Label l1 = new Label();
			Label l2 = new Label();
			l1.setId("l1");
			l2.setId("l2");
			FXRManager.translateComponents(getClass(), l1,l2);
			Label infoLabel = new Label(l1.getText()+" "+Integer.toString(player.getId())+" "+l2.getText());
			sceneMover.moveToScene(event, MenuSceneController.class);
			new FxInformationScene(InfoType.INFORMATION, infoLabel).
					setRootStyle("-fx-background-color:#FAEBD7");
		}
		return null;
	}

}
