package darts_counter_plugin;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;



public class SubstringTest {
	
	@Test
	public void substr() {
		String s = "T-20";
		String r = "20";
		String sub = s.substring(2,s.length());
		assertEquals(sub, r);
	}

}
