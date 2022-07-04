package com.facebook.register;

import org.testng.annotations.Test;
import commons.BaseTest;
import pageObjects.Facebook.LoginPageObject;
import pageObjects.Facebook.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13_Element_Undisplay extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}
	
	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		
		// Neu 1 cai ham chi mong doi de verify element displayed thoi - ket hop ca wait + isDisplayed
		// waitForElementVisible
		// isElementDisplayed
		verifyTrue(loginPage.isEmailAddressTextboxDisplay());
	}
	
	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {
		// Verify False - mong doi Confirm Email displayed( true)
//		loginPage.enterToEmailAddressTextbox("automation@gmail.com");
//		loginPage.sleepInSecond(2);
//		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		// Verify False - mong doi Confirm Email Undisplayed( false)
		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(2);
//		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
		
	}

	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(2);
		
		// Khi close form Register di thi confirm emai ko con trong DOM nua
		// nen ham findElement se bi fail vi ko tim thay element
		// 1 - No se cho het Timeout cua implicit: 30s
		// 2 - No se danh fail testcase tai dung thoi diem nay luon
		// 3 - Ko chay cac step con lai nua
		
		
		// Verify False - mong doi Confirm Email Undisplayed( false)
		// verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
