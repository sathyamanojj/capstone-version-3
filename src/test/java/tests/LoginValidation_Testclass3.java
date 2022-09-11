package tests;

import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.keyword;

public class LoginValidation_Testclass3 extends BaseTest{
	
/**
 * Test Class-3
1. Create a testng.xml with parameter as valid and invalid credentials and use
them in the test class
2. Create the test case for login validation
3. Validate login and logout
4. Validate invalid login and logout
 */
	
	
	keyword kw;

	@BeforeClass
	public void beforeClass() {
		kw = new keyword(driver);
	}

	
	@Parameters({"validUsername", "validPassword"})	
	@Test(priority = 1)
	public void validUsernameValidPassword(String username, String password) {
		kw.login(username, password);
		Reporter.log(username);
		Reporter.log(password);
	}

	
	 @Parameters({"invalidUsername", "invalidPassword"})	
	 
	@Test(priority = 2)
	public void invalidUsernameValidPassword(String username, String password) {
		 driver.navigate().back();
		 driver.navigate().back();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		kw.invalidLogin(username, password);
		Reporter.log(username);
		Reporter.log(password);
	}

}
