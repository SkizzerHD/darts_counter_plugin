package de.counter.plugin.x01game.data;


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
public class X01Player {
	
	private int id;
	private int score;
	private double avg;
	private String name;
	private int ammountThrows;
	
	public X01Player(int id, int score) {
		this.id = id;
		this.score = score;
		avg = 0.00;
		name = "Player "+id;
	}
}
