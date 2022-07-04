package com.jquery;

import org.testng.annotations.Test;
import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_11_Upload_Files extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	String csharpFileName = "CSharp.png";
	String javaFileName = "java.png";
	String pythonFileName = "python.png";
	String rubyFileName = "ruby.png";
	String[] multipleFileNames = { csharpFileName, javaFileName, pythonFileName, rubyFileName };

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Upload_01_One_Files_Per_Time() {
		// Step 01 - Load single file
		homePage.uploadMultipleFiles(driver, csharpFileName);

		// Step 02 - Verify single file loaded success
		Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));

		// Step 03 - Click to start button
		homePage.clickToStartButton();

		// Step 04 - Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(csharpFileName));

		// Step 05 - Verify single file image uploaded success
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(csharpFileName));
	}

	@Test
	public void Upload_02_Multiple_Files_Per_Time() {
		// Reset Page
		homePage.refreshCurrentPage(driver);
		
		// Step 01 - Load multiple file
		homePage.uploadMultipleFiles(driver, multipleFileNames);

		// Step 02 - Verify multiple file loaded success
		// csharpFileName, javaFileName, pythonFileName, rubyFileName
		Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(rubyFileName));

		// Step 03 - Click to start button
		homePage.clickToStartButton();

		// Step 04 - Verify multiple file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(rubyFileName));

		// Step 05 - Verify multiple file image uploaded success
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(rubyFileName));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
