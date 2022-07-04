package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_09_Dynamic_Locator2 extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName, emailAddress, validPassword;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		validPassword = "123456";
	}

	@Test
	public void User_01_Register_Login() {
		registerPage = homePage.openRegisterPage();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickToLogoutLink();

		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(validPassword);

		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

		customerInforPage = homePage.openMyAccountPage();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}

	@Test
	public void User_02_Switch_Page() {
		
		// Customer Infor -> Address
		addressPage = customerInforPage.openAddressPage(driver);
		
		// Address -> My Product Review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		
		// My Product Review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPoint(driver);
		
		//Reward Point -> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		
		// Address -> Reward Point
		rewardPointPage = addressPage.openRewardPoint(driver);
		
		// Reward Point -> My Product Review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
		// My product Review -> Address
		addressPage = myProductReviewPage.openAddressPage(driver);
		
		// Address- > CustomerInfor
		customerInforPage = addressPage.openCustomerInforPage(driver);
		
		// CustomerInfor -> My product Review
		myProductReviewPage = customerInforPage.openMyProductReviewPage(driver);
	}
	
	@Test
	public void User_03_Dynamic_Page_Method_01() {
		// My Product Review -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		//Reward Point -> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		
		// Address -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		// Reward Point -> My Product Review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		
		// My Product Review -> Customer Info
		customerInforPage = (UserCustomerInforPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");
	}
	
	@Test
	public void User_03_Dynamic_Page_Method_02() {
		// Customer Info -> My Product Review
		customerInforPage.openPagesAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		// My Product Review -> Reward Point
		myProductReviewPage.openPagesAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
		//Reward Point -> Address
		rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		
		// Address -> Reward Point
		addressPage.openPagesAtMyAccountByName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
		// Reward Point -> My Product Review
		rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		// My Product Review -> Customer Info
		myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");
		customerInforPage = PageGeneratorManager.getUserCustomerInforPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
