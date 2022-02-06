package darts_counter_plugin;

import org.junit.jupiter.api.Test;

import de.counter.plugin.cricket.controller.CricketGameSceneController;

public class GetFieldsTest {
	
	@Test
	public void sysoutFields() {
		System.out.println(CricketGameSceneController.class.getDeclaredFields()[0]);
	}

}
