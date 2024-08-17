package shoppingCart;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.genericutility.BaseClass;
import com.tricentis.genericutility.ListenerUtility;
import com.tricentis.objectrepository.HomePage;
@Listeners(ListenerUtility.class)
public class TC_DWS_003_Test extends BaseClass{
	@Test(groups = "system")
	public void addProduct() {
		test=extReport.createTest("addProduct");
		hp=new HomePage(driver);
		hp.getAddToCartButtons().get(1).click();
		boolean msgStatus = hp.getProductAddedMsg().isDisplayed();
		Assert.assertEquals(msgStatus, true,"Product failed to add");
		test.log(Status.PASS, "Product has been added");
		wait.until(ExpectedConditions.invisibilityOf(hp.getProductAddedMsg()));
	}
}
