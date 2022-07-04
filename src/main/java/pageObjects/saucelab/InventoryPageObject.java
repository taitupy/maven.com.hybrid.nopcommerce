package pageObjects.saucelab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.saucelab.InventoryPageUI;

public class InventoryPageObject extends BasePage{
	WebDriver driver;

	public InventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropDown(String itemText) {
		waitForElementClickabe(driver, InventoryPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, InventoryPageUI.SORT_DROPDOWN, itemText);
	}

	public boolean isProductNameSortAscending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText= new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		
		List<String> productNameTextClone = new ArrayList<String>();
		for (String product : productNameText) {
			productNameTextClone.add(product);
		}
		
		Collections.sort(productNameTextClone);
		
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNameSortDecending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText= new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		
		List<String> productNameTextClone = new ArrayList<String>();
		for (String product : productNameText) {
			productNameTextClone.add(product);
		}
		
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);
		
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductPriceSortAscending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productPriceValue= new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		
		List<Float> productPriceTextClone = new ArrayList<Float>();
		for (Float productPrice : productPriceValue) {
			productPriceTextClone.add(productPrice);
		}
		
		Collections.sort(productPriceTextClone);
		
		return productPriceValue.equals(productPriceTextClone);
	}

	public boolean isProductPriceSortDecending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productPriceValue= new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		
		List<Float> productPriceTextClone = new ArrayList<Float>();
		for (Float productPrice : productPriceValue) {
			productPriceTextClone.add(productPrice);
		}
		
		Collections.sort(productPriceTextClone);
		Collections.reverse(productPriceTextClone);
		
		return productPriceValue.equals(productPriceTextClone);
	}

}
