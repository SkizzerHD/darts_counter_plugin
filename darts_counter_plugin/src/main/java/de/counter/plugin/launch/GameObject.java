package de.counter.plugin.launch;

import javafx.event.Event;

/**
 * 
 * @author David Baumer
 * All gameObjects must implement this interface because they 
 * they must be able to end the game
 * @param <T>
 *
 */
public interface GameObject {
	
	 //you can return any object you want even simply zero
	 Object checkEnd(Event event);

}
