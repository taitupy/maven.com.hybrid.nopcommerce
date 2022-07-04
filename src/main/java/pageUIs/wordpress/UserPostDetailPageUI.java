package pageUIs.wordpress;

public class UserPostDetailPageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//h1[text()='%s']";
	public static final String POST_CURRENT_DATE_TEXT_BY_POST_TITLE = "xpath=//article//h1[text()='%s']/following-sibling::div//time[contains(text(),'16, 2022')]";
	public static final String POST_BODY_TEXT = "xpath=//article//h1[text()='%s']/ancestor::header/following-sibling::div/p[text()='%s']";
	public static final String POST_AUTHOR_TEXT = "xpath=//article//h1[text()='%s']/following-sibling::div//span[@class='author vcard']/a[text()='%s']";
}
