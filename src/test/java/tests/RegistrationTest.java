package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegistrationTest {
	
	Logger logger = LogManager.getLogger(DownloadTest.class.getName());
	
	@Test
	public void register() {
		
		System.out.println("Modification done in Registration in dev ");
		
		logger.debug("Invoking Register Test");
		Assert.assertEquals(false, false);
	}

}
