package PageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageObject extends BasePageFactory{
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(id = "FirstName")
	private WebElement firstnameTextbox;
	
	@FindBy(id = "LastName")
	private WebElement lastnameTextbox;
	
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(xpath = "//button[@id='register-button']")
	private WebElement registerButton;
	
	@FindBy(id = "FirstName-error")
	private WebElement firstnameErrorMessage;
	
	@FindBy(id = "LastName-error")
	private WebElement lastnameErrorMessage;
	
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;
	
	@FindBy(id = "Password-error")
	private WebElement passwordErrorMessage;
	
	@FindBy(id = "ConfirmPassword-error")
	private WebElement confirmpasswordErrorMessage;
	
	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']//li")
	private WebElement existingEmailErrorMessage;
	
	public void clickToRegisterButton() {
		waitForElementClickabe(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getErrorMessageAtFirstnameTextbox() {
		waitForElementVisible(driver, firstnameErrorMessage);
		return getElementText(driver, firstnameErrorMessage);
	}

	public String getErrorMessageAtLastnameTextbox() {
		waitForElementVisible(driver,lastnameErrorMessage);
		return getElementText(driver, lastnameErrorMessage);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, confirmpasswordErrorMessage);
		return getElementText(driver, confirmpasswordErrorMessage);
	}

	public void inputToFirstnameTextbox(String firstName) {
		waitForElementVisible(driver, firstnameTextbox);
		sendkeyToElement(driver, firstnameTextbox, firstName);
	}

	public void inputToLastnameTextbox(String lastname) {
		waitForElementVisible(driver, lastnameTextbox);
		sendkeyToElement(driver, lastnameTextbox, lastname);
		
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
		
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendkeyToElement(driver, confirmPasswordTextbox, confirmPassword);
		
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	public void clickToLogoutLink() {
		waitForElementClickabe(driver, logoutLink);
		clickToElement(driver, logoutLink);
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}
}
