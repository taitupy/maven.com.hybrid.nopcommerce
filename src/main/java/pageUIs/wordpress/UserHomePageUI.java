package pageUIs.wordpress;

public class UserHomePageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//h2/a[text()='%s']";
	public static final String POST_CURRENT_DATE_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']/parent::h2/following-sibling::div//time[contains(text(),'%s')]";
	public static final String POST_BODY_TEXT = "xpath=//article//a[text()='%s']//parent::h2/parent::header/following-sibling::div/p[text()='%s']";
	public static final String POST_AUTHOR_TEXT = "xpath=//article//a[text()='%s']/parent::h2/following-sibling::div//span[@class='author vcard']/a[text()='%s']";
	public static final String SEARCH_TEXTBOX = "name=s";
	public static final String SEARCH_BUTTON = "css=button.wp-block-search__button";
	
}
