package books;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.genericutility.BaseClass;
import com.tricentis.genericutility.ExcelUtility;
import com.tricentis.genericutility.ListenerUtility;
import com.tricentis.objectrepository.HomePage;


@Listeners(ListenerUtility.class)
public class TC_DWS_005_Test extends BaseClass{
	@Test(groups = "smoke")
	public void clickOnBooks() throws EncryptedDocumentException, IOException {
		test=extReport.createTest("clickOnBooks");
		hp=new HomePage(driver);
		hp.getBooksLink().click();
		excelLib=new ExcelUtility();
		String expectedTitle = excelLib.getStringDataFromExcel("Books", 1, 0);
		Assert.assertEquals(driver.getTitle(), expectedTitle,"Books page is not displayed");
		test.log(Status.PASS, "Books is displayed");
	}
}
