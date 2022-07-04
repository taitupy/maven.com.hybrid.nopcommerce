package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
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

public class Level_08_Switch_Role extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInforPageObject userCustomerInforPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private String userEmailAddress, userPassword, adminEmailAddress, adminPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userEmailAddress = "automationfc123@gmail.com";
		userPassword = "111111";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage = userHomePage.openLoginPage();

		// Login as User Role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

		// HomePage -> Customer Infor Page
		userCustomerInforPage = userHomePage.openMyAccountPage();

		// Customer Infor Page -> HomePage
		userHomePage = userCustomerInforPage.clickLogoutAtUserPage(driver);

		// User HomePage -> Open Admin Page -> Login Page (Admin)
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_TESTING_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		// Login as Admin Role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

		// Dashboard Page -> Click Logout -> LoginPage (Admin)
		adminLoginPage = adminDashboardPage.clickLogoutAtAdminPage(driver);
	}

	@Test
	public void Role_02_Admin_To_User() {
		// Login Page (Admin) -> Open Portal url -> HomePage (User)
		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_TESTING_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		// HomePage -> Login (User)
		userLoginPage = userHomePage.openLoginPage();

		// Login as User Role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
