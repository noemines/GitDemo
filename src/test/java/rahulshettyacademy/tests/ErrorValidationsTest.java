package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshetyacademy.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest{

	@Test (groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("nirr24@hotmail.com", "leamonir-A7");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
System.out.println("t3es");
System.out.println("gfdghf");
System.out.println("sgdsgvdsgv");
	}
	
	@Test
	public void ProdutErrorValidation() throws IOException,  InterruptedException{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("nirr24@hotmail.com", "leamnir-A7");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage =productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
	}

}
