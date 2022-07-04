package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.datatest.nopcommerce.InforCustomerData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import ultilities.DataUtil;

public class Level_21_Data_Test_IV_Data_Driven extends BaseTest{
	WebDriver driver;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	UserRegisterPageObject registerPage;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, invalidEmail, notFoundEmail,existingEmail, password;
	UserCustomerInforPageObject myAccountPage;
	InforCustomerData inforCustomerData;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		inforCustomerData = InforCustomerData.getInforCustomer();
		
		System.out.println("Pre-condition - Step 01: Click to Register link");
		registerPage = homePage.openRegisterPage();

		System.out.println("Pre-condition - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(inforCustomerData.getFirstName());
		registerPage.inputToLastnameTextbox(inforCustomerData.getLastName());
		registerPage.inputToEmailTextbox(inforCustomerData.getExistingEmail());
		registerPage.inputToPasswordTextbox(inforCustomerData.getPassword());
		registerPage.inputToConfirmPasswordTextbox(inforCustomerData.getPassword());

		System.out.println("Pre-condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition - Step 05: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.openLoginPage();

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(inforCustomerData.getInvalidEmail());
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(inforCustomerData.getNotFoundEmail());
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextboxIsNotRegisterYet(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(inforCustomerData.getExistingEmail());
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextboxIsNotRegisterYet(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(inforCustomerData.getExistingEmail());
		loginPage.inputToPasswordTextbox("222333");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextboxIsNotRegisterYet(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_Password() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(inforCustomerData.getExistingEmail());
		loginPage.inputToPasswordTextbox(inforCustomerData.getPassword());
		
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		myAccountPage = homePage.openMyAccountPage();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
