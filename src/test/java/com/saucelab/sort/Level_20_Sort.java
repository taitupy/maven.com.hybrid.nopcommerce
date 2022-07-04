package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.saucelab.InventoryPageObject;
import pageObjects.saucelab.LoginPageObject;
import pageObjects.saucelab.PageGeneratorManager;

public class Level_20_Sort extends BaseTest{
	WebDriver driver;
	LoginPageObject loginPage;
	InventoryPageObject inventoryPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		driver.manage().window().maximize();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clickToLoginButton();
	}
	
	@Test
	public void Sort_01_Name() {
		inventoryPage.selectItemInSortDropDown("Name (A to Z)");
		inventoryPage.sleepInSecond(2);
		verifyTrue(inventoryPage.isProductNameSortAscending());
		
		inventoryPage.selectItemInSortDropDown("Name (Z to A)");
		inventoryPage.sleepInSecond(2);
		verifyTrue(inventoryPage.isProductNameSortDecending());
	}

	@Test
	public void Sort_02_Name() {
		inventoryPage.selectItemInSortDropDown("Name (A to Z)");
		inventoryPage.sleepInSecond(2);
		
		verifyTrue(inventoryPage.isProductNameSortDecending());
	}

	@Test
	public void Sort_03_Price() {
		inventoryPage.selectItemInSortDropDown("Price (low to high)");
		inventoryPage.sleepInSecond(2);
		verifyTrue(inventoryPage.isProductPriceSortAscending());
		
		inventoryPage.selectItemInSortDropDown("Price (high to low)");
		inventoryPage.sleepInSecond(2);
		verifyTrue(inventoryPage.isProductPriceSortDecending());
	}

	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-Condition: Close browser '" + browserName + "'");
		closeBrowserAndDriver();
	}
}
