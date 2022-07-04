package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_Cookies extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String firstName, lastName, emailAddress, password;
	public static Set<Cookie> loggedCookies;

	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Class Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";

		log.info("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Register - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Register - Step 08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Register - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

		log.info("Pre-condition - Step 10: Navigate to 'Login' page");
		loginPage = homePage.openLoginPage();

		log.info("Pre-condition - Step 11: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		log.info("Pre-condition - Step 12: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		log.info("Pre-condition - Step 13: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		loggedCookies = homePage.getAllCookies(driver);
		for (Cookie cookie : loggedCookies) {
			System.out.println("Cookie at common class: " + cookie);
		}
	}

	@AfterTest
	public void afterClass() {
		driver.quit();
	}
}
