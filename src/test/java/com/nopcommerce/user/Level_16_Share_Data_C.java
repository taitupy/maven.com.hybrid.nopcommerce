package com.nopcommerce.user;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookies;
import com.nopcommerce.common.Common_01_Register_End_User;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class Level_16_Share_Data_C extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	String emailAddress, validPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = Common_01_Register_End_User.emailAddress;
		validPassword = Common_01_Register_End_User.password;
		
		log.info("Pre-condition - Step 01: Navigate to 'Login' page");
		loginPage = homePage.openLoginPage();

		log.info("Pre-condition - Step 02: Set cookie and reload page");
		loginPage.setCookies(driver, Common_01_Register_Cookies.loggedCookies);
		for (Cookie cookie : Common_01_Register_Cookies.loggedCookies) {
			System.out.println("Cookie at C class: " + cookie);
		}
		loginPage.refreshCurrentPage(driver);
		
		log.info("Pre-condition - Step 03: Verify 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Test
	public void User_01_Empty_Data() {
		
	}
	
	@Test
	public void User_02_Relative_Product_Name() {
		
		
	}
	
	@Test
	public void User_03_Absolute_Product_Name() {
		
		
	}
	
	@Test
	public void User_04_Parent_Category() {
		
		
	}
	
	@Test
	public void User_05_Incorrect_Manufactorer() {
		
		
	}
	
	@Test
	public void User_06_Correct_Manufactorer() {
		

	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
