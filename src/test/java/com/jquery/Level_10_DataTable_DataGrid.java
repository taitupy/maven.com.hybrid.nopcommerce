package com.jquery;

import org.testng.annotations.Test;
import commons.BaseTest;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_10_DataTable_DataGrid extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;
	List<String> actualallCountryValues;
	List<String> expectedallCountryValues;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Data_Tabble_01() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("20"));
		
		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("7"));
		
		homePage.openPagingByPageNumber("15");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("15"));
	}

//	@Test
	public void Data_Tabble_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTextboxByLable("Country", "Argentina");
		homePage.enterToHeaderTextboxByLable("Females", "338282");
		homePage.enterToHeaderTextboxByLable("Males", "349238");
		homePage.enterToHeaderTextboxByLable("Total", "687522");
		homePage.sleepInSecond(1);
		
		homePage.enterToHeaderTextboxByLable("Country", "AFRICA");
		homePage.enterToHeaderTextboxByLable("Females", "12253515");
		homePage.enterToHeaderTextboxByLable("Males", "12599691");
		homePage.enterToHeaderTextboxByLable("Total", "24853148");
		homePage.sleepInSecond(1);
		
	}
	
//	@Test
	public void Data_Tabble_03_Enter_To_Header() {
		// Đọc dữ liệu của file country.txt ra
		// Lưu vào 1 List<String> Expected Value = expectedAllCountryValues
		
		// Actual Value
		actualallCountryValues = homePage.getValueEachRowAtAllPage();
		
		Assert.assertEquals(actualallCountryValues, expectedallCountryValues);
		
	}
	
//	@Test
	public void Data_Tabble_04_Action_At_Any_Row() {
		homePage.clickToLoadButton();
		homePage.sleepInSecond(2);
		
		// Value de nhap lieu - tham so 1
		// Row number : tai row nao
		// Ex: Nhap vao textbox tai dong so 3/ 5/ 2
		// Column name: Album/ Artirs/ Year/ Price
		homePage.enterToTextboxByColumnNameAtRowNumber("Album", "1", "Snow man");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Artist", "4", "Michale Jackson");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Year", "3", "1977");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Price", "2", "200");
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "5", "Japan");
		homePage.sleepInSecond(2);
		
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
		
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "2");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "4");
		homePage.enterToTextboxByColumnNameAtRowNumber("Album", "1", "Snow man");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Artist", "4", "Michale Jackson");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Year", "3", "1977");
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Price", "2", "200");
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "5", "Japan");
		homePage.sleepInSecond(2);
		
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
		
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "2");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "4");
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(2);
		
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		homePage.sleepInSecond(2);
		
		homePage.clickToIconByRowNumber("3", "Move Up");
		homePage.sleepInSecond(2);
		
		homePage.clickToIconByRowNumber("5", "Remove Current Row");
		homePage.sleepInSecond(2);
		
		homePage.clickToIconByRowNumber("4", "Remove Current Row");
		homePage.sleepInSecond(2);
		
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		homePage.sleepInSecond(2);
		
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		homePage.sleepInSecond(2);
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(2);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
