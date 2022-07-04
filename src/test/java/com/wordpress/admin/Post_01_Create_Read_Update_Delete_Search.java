package com.wordpress.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserSearchPostPO;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
	private WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminPostSearchPO adminPostSearchPage;
	AdminPostAddNewPO adminPostAddNewPage;
	UserHomePO userHomePage;
	UserPostDetailPO userPostDetailPage;
	UserSearchPostPO userSearchPostPage;

	String adminUserName = "automationfc";
	String adminPassword = "automationfc";
	String searchPostUrl;
	int randomNumber = generateFakeNumber();
	String postTitle = "Live Coding Title " + randomNumber;
	String postBody = "Live Coding Body " + randomNumber;
	String editPostTitle = "Edit Title " + randomNumber;
	String editPostBody = "Edit Body " + randomNumber;
	String authorName = "Automationfc";
	String adminUrl, endUserUrl;
	String currentDay = getCurrentDay();

	@Parameters({ "browser", "urlAdmin" , "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-Condition - Step 01: Open browser and admin Site");
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-Condition - Step 02: Enter to Username textbox with value: " + adminUserName);
		adminLoginPage.enterToUsernameTextbox(adminUserName);

		log.info("Pre-Condition - Step 03: Enter to Password textbox: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);

		log.info("Pre-Condition - Step 04: Click to Login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
//		adminPostSearchPage = PageGeneratorManager.getAdminPostSearchPage(driver);

		log.info("Create_Post - Step 02: Get 'Search Posts' Page Url");
		searchPostUrl = adminPostSearchPage.getPageUrl(driver);

		log.info("Create_Post - Step 03: Click to 'Add New' button");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
//		adminPostAddNewPage = PageGeneratorManager.getAdminPostAddNewPage(driver);

		log.info("Create_Post - Step 04: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);

		log.info("Create_Post - Step 05: Enter to body");
		adminPostAddNewPage.enterToAddNewPostBody(postBody);

		log.info("Create_Post - Step 06: Click to Publish button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();

		log.info("Create_Post - Step 07: Click to Pre-Publish button");
		adminPostAddNewPage.clickToPrePublishButton();

		log.info("Create_Post - Step 08: Verify 'Post published' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
	}

	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.OpenSearchPostPageUrl(searchPostUrl);

		log.info("Search_Post - Step 02: Enter to 'Search' textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);

		log.info("Search_Post - Step 03: Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Search_Post - Step 04: Verify Search table contains '" + postTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", postTitle));

		log.info("Search_Post - Step 05: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Search_Post - Step 06: Open End User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search_Post - Step 07: Verify post infor displayed at Home Page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(postTitle, currentDay));

		log.info("Search_Post - Step 08: Click to Post title");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);

		log.info("Search_Post - Step 09: Verify Post infor displayed at Post detail page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostCurrentDay(postTitle, currentDay));
	}

	@Test
	public void Post_03_Edit_Post() {
		log.info("Edit_Post - Step 01: Open Admin Site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Edit_Post - Step 02: Click to 'Post' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to 'Search' textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);

		log.info("Edit_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Edit_Post - Step 05: Click to Post Title detail link and navigate to Edit Post page");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitle);
		
		log.info("Edit_Post - Step 06: Enter to Edit post title");
		adminPostAddNewPage.enterToAddNewPostTitle(editPostTitle);

		log.info("Edit_Post - Step 07: Enter to Edit body");
		adminPostAddNewPage.enterToEditPostBody(editPostBody);

		log.info("Edit_Post - Step 08: Click to Update button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();
		
		log.info("Edit_Post - Step 09: Verify 'Post updated.' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post updated."));
		
		log.info("Edit_Post - Step 10: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.OpenSearchPostPageUrl(searchPostUrl);

		log.info("Edit_Post - Step 11: Enter to 'Search' textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);

		log.info("Edit_Post - Step 12: Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Edit_Post - Step 13: Verify Search table contains '" + editPostTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", editPostTitle));

		log.info("Edit_Post - Step 14: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Edit_Post - Step 15: Open End User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Edit_Post - Step 16: Verify post infor displayed at Home Page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(editPostTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(editPostTitle, currentDay));

		log.info("Edit_Post - Step 17: Click to Post title");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);

		log.info("Edit_Post - Step 18: Verify Post infor displayed at Post detail page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(editPostTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostCurrentDay(editPostTitle, currentDay));
	}

	@Test
	public void Post_04_Delete_Post() {
		log.info("Delete_Post - Step 01: Open Admin Site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Delete_Post - Step 02: Click to 'Post' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Delete_Post - Step 03: Enter to 'Search' textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);

		log.info("Delete_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Delete_Post - Step 05: Select Post detail checkbox");
		adminPostSearchPage.selectPostCheckboxByTitle(editPostTitle);
		
		log.info("Delete_Post - Step 06: Select 'Move to Trash' item in dropdown");
		adminPostSearchPage.selectTextItemInActionDropdown("Move to Trash");
		
		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearchPage.clickToApplyButton();
		
		log.info("Delete_Post - Step 08: Verify '1 post moved to the Trash.' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash."));
		
		log.info("Delete_Post - Step 09: Enter to 'Search' textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);

		log.info("Delete_Post - Step 10: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Delete_Post - Step 11: Verify 'No posts found.' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed("No posts found."));
		
		log.info("Delete_Post - Step 12: Open End User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Delete_Post - Step 13: Verify post title undisplayed at Home Page");
		verifyTrue(userHomePage.isPostInforUndisplayedWithPostTitle(editPostTitle));
		
		log.info("Delete_Post - Step 14: Enter to Search textbox");
		userHomePage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 15: Click to 'Search' button");
		userSearchPostPage = userHomePage.clickToSearchButton();
		
		log.info("Delete_Post - Step 16: Verify 'Nothing Found' message is displayed");
		verifyTrue(userSearchPostPage.isNothingFoundMessageDisplayed("Nothing Found"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
