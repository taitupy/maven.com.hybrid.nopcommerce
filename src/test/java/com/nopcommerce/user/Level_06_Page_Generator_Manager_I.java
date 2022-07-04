package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_06_Page_Generator_Manager_I extends BaseTest{
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, invalidEmail, notFoundEmail,existingEmail, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		// 1
		homePage = new UserHomePageObject(driver);
		
		firstName = "Automation";
		lastName  = "FC";
		invalidEmail  = "afc@ss@.&%";
		existingEmail = "afc" + generateFakeNumber() + "@mail.vn";
		notFoundEmail  = "afc" + generateFakeNumber() + "@mail.com";
		password  = "123456";
		
		System.out.println("Pre-condition - Step 01: Click to Register link");
		homePage.openRegisterPage();
		
		// 2
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Pre-condition - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Pre-condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
		
		// 3
		homePage = new UserHomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		homePage.openLoginPage();
		
		// Từ trang Home- Click Login Link -> Qua trang Login
		// 4
		loginPage = new UserLoginPageObject(driver);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.openLoginPage();
		
		// Từ trang Home- Click Login Link -> Qua trang Login
		// 5
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(invalidEmail);
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.openLoginPage();
		
		// Từ trang Home- Click Login Link -> Qua trang Login
		// 6
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(notFoundEmail);
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextboxIsNotRegisterYet(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePage.openLoginPage();
		
		// Từ trang Home- Click Login Link -> Qua trang Login
		// 7
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("");
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextboxIsNotRegisterYet(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		homePage.openLoginPage();

		// Từ trang Home- Click Login Link -> Qua trang Login
		// 8
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("222333");
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextboxIsNotRegisterYet(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_Password() {
		homePage.openLoginPage();

		// Từ trang Home- Click Login Link -> Qua trang Login
		// 9
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(password);
		
		loginPage.clickToLoginButton();
		
		// Login thành công -> HomePage
		// 10
		homePage = new UserHomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
