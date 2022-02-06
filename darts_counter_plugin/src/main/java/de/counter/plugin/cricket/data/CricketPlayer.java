package de.counter.plugin.cricket.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CricketPlayer {
	
	private int id;
	private String name;
	private List<CricketNumber>numbers;
	
	public CricketPlayer(int id) {
		this.id = id;
		this.name = "Player "+id;
		setCricketNumbers();
	}
	
	private void setCricketNumbers() {
		numbers = new ArrayList<>();
		for(int i = 15; i<=20;i++) {
			numbers.add(new CricketNumber(i,Integer.toString(i)));
		}
		numbers.add(new CricketNumber(25, "BULL"));
	}
	
}
