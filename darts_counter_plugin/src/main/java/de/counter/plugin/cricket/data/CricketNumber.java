package de.counter.plugin.cricket.data;

import lombok.Data;

@Data
public class CricketNumber {
	
	private int id;
	private String number;
	private int hits;
	private boolean finished;
	
	public CricketNumber(int id, String number) {
		this.id = id;
		this.number = number;
		hits =0;
		finished = false;
	}

}
