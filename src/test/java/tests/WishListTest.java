package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishListTest {
	
	Logger logger = LogManager.getLogger(DownloadTest.class.getName());
	
	@Test
	public void wishList() {
		logger.debug("Invoking WishList Test");
		Assert.assertEquals(false, false);
	}

}
