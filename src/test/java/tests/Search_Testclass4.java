package tests;

import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.Login;
import utils.keyword;

public class Search_Testclass4 extends BaseTest {

	/**
	 * Test Class-4
1. Use navigation, to search for mobile such as mi mobile
2. Validate the results, for instance, 1-16 of 264 results for mi mobile
3. Select Avg. Customer Review as 4 stars & up
4. Select the first mobile,
a. Validate the change in the title, whether it is relevant to the selected
mobile. 
5. Get the mobile price and validate it
6. Validate the Delivery location 
a. Click on the Select delivery location
7. Popup window and pincode validation
a. Wait for the pop up Choose your location to appear
b. Enter the pincode and click on the Apply button
c. Validate the change in the delivery location.
d. It should display Deliver to<citypincode>
8. Click on the sponsored link as shown below :
a. There is no IFrame to click so  click on the new product which will stay on the same page.
	 */
	
	
	keyword kw;

	@BeforeClass
	public void beforeClass() {
		kw = new keyword(driver);
	}

//	1. Use navigation, to search for mobile such as mi mobile
	
	@Test(priority = 1)
	public void search_MiMobile() {
		kw.amazonSearchbox();

	}

	
// 2. Validate the results, for instance, 1-16 of 264 results for mi mobile
	  
	  @Test(dependsOnMethods={"search_MiMobile"}) 
	  public void search_Results()
	  { kw.searchResult(); }
	 
//3. Select Avg. Customer Review as 4 stars & up
	  
	 @Test(dependsOnMethods={"search_Results"})
	//@Test(dependsOnMethods = { "search_MiMobile" })
	public void customer_Review() {
		kw.average4StarRating();
	}

//Select the first mobile, and in this case, Redmi Note 8 (Moonlight White, 6GB RAM, 128GB Storage)	
	 
	@Test(dependsOnMethods = { "customer_Review" })
	public void first_Mobile() {
		kw.pageWait();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kw.selectMobile();

	}

//Validate the change in the title, whether it is relevant to the selected mobile
	@Test(dependsOnMethods = { "first_Mobile" })
	public void validate_Mobile() {
		try {
			kw.switchTabs();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("switched to different iframe to validate the mobile title");
		String actual = kw.getCurrentPageTitle();
		Reporter.log("current page title is " + actual);

	}

//Get the mobile price and validate it
	@Test(dependsOnMethods = { "validate_Mobile" })
	public void validate_Mobile_Price() {
		kw.mobilePrice();
	}

//Validate the Delivery location 
	// a. Click on the Select delivery location

	@Test(dependsOnMethods = { "validate_Mobile_Price" })
	public void validate_Delivery_Location() {
		kw.deliveryLocation();
		Reporter.log("Delivery location selected successfully");

	}

//	Popup window and pincode validation
//	a. Wait for the pop up Choose your location to appear
	// b. Enter the pincode and click on the Apply button
	// c. Validate the change in the delivery location.
	// d. It should display Deliver to<citypincode>
	@Test(dependsOnMethods = { "validate_Delivery_Location" })
	public void validate_Pincode() {
		kw.enterPincode();
		Reporter.log("Pincode entered successfully");

	}

	/********
	 * Sponsored link does not have a Iframe and does not navigate to separate
	 * window
	 *************/
//	8. Click on the sponsored link as shown below :
//	a. There is no IFrame to click so  click on the new product which will stay on the same page.

	@Test(dependsOnMethods = { "validate_Pincode" })
	public void sponsored_Link() {
		kw.pageWait();
		kw.clickSponsored();
		Reporter.log("first item from sponsored item is chosen");
		driver.navigate().back();
	}

//9. Click on Add to Cart
//a. Wait for the window to be displayed
//	b. Validate the Added to Cart message
//	c. Close the Added to Cart window
	
	@Test(dependsOnMethods = { "sponsored_Link" })
	public void add_To_Cart() {

		kw.addCartItem();
		Reporter.log("added to cart successfully");
		kw.pageWait();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		kw.addedCartItem();
		kw.pageWait();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		kw.closeSheet();

	}

	// 10. Use JavaScriptExecutor to scroll the page downwards until the ‘Technical
	// Details’ are displayed
	
	@Test(dependsOnMethods = { "add_To_Cart" })
	public void tecnical_Details() {
		//kw.pageWait();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		kw.tecDetails();

	}

}
