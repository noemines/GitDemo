package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshetyacademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";

	@Test(dataProvider ="getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException {
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password") );
		
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage =productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		
		ConfirmationPage confirmationPage =checkoutPage.submitOrder();
		String confirmMessage =confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	//to verify if ZARA COAT 3 is displayed in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		//ZARA COAT 3
		ProductCatalogue productCatalogue=landingPage.loginApplication("nirr24@hotmail.com", "leamnir-A7");
		OrderPage ordersPage =productCatalogue.goToOrdersPage();
		Assert.assertTrue( ordersPage.VerifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "nirr24@hotmail.com");
//		map.put("password", "leamnir-A7");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "ma_chavez_gaytan@hotmail.com");
//		map1.put("password", "@Mario6s");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object [] [] {{data.get(0)}, {data.get(1)}};
	}
}
