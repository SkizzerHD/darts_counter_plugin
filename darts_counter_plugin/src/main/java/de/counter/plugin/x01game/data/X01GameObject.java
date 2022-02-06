package de.counter.plugin.x01game.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import de.counter.plugin.launch.GameObject;
import javafx.event.Event;
import lombok.Data;

/**
 * 
 * @author David Baumer
 * 
 * @Data generates all the boilerplate that is normally 
 * associated with simple POJOs (Plain Old Java Objects) and beans
 *
 */
@Data
@Component
public class X01GameObject implements GameObject {

	private int score;
	private X01Player player;
	private List<X01Player> playerList;
	private boolean doubleOut;
	
	public void fillPlayerList(int amountPlayers) {
		doubleOut = false;
		playerList = new ArrayList<>();
		for(int i=0; i<amountPlayers;i++) {
			playerList.add(new X01Player(i+1,score));
		}
	}

	public void calculatePlayerAvg() {
		double gs = (double)score;
		double ps = (double)player.getScore();
		//avg formule for 3 throws
		player.setAvg((gs-ps)/player.getAmmountThrows()*3);
	}
	
	//Game is over if one player checked out
	public Object checkEnd(Event event) {
		int i = 0;
		for(X01Player p : playerList) {
			if(p.getScore() > 0) {	
			}else {
				i++;
			}
		}
		if(i== 1) {
			return true;
		}
		return false;
	}
}
