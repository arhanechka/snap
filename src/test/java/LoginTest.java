import com.snaptest.testing.common.Data;
import com.snaptest.testing.pages.FriendPage;
import com.snaptest.testing.pages.LoginPage;
import com.snaptest.testing.pages.MainPage;
import com.snaptest.testing.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private StartPage homePage;
    private MainPage mainPage;
    private FriendPage freindPage;

    @BeforeTest
    public void initComponents(){
        loginPage = new LoginPage(getDriver());
        homePage = new StartPage(getDriver());
        mainPage = new MainPage(getDriver());
        freindPage = new FriendPage(getDriver());
    }

    @BeforeMethod
    public void closeActivity() throws InterruptedException{
        driver.resetApp();
    }

    @Test
    public void loginTestWithValidParameters(){
        Assert.assertTrue(homePage.pageIsDisplayed(), "Home Page is not displayed");
        homePage.tapLogin();
        Assert.assertTrue(loginPage.pageIsDisplayed(), "Login Page is not displayed");
        loginPage.setUsernameFiels(Data.VALID_USERNAME);
        loginPage.setPasswordFiels(Data.VALID_PASSWORD);
        loginPage.tapLoginButton();
        loginPage.tapTurnOnCameraButton();
        loginPage.handleAlert(2);
        loginPage.handleAlert(1);
        Assert.assertTrue(mainPage.pageIsDisplayed(), "Main Page is not displayed");
        mainPage.searchAFriend(Data.VALID_FRIEND_NAME);
        Assert.assertTrue(mainPage.isFriendPresent(Data.VALID_FRIEND_NAME), "Specified friend is not present");
        Assert.assertFalse(mainPage.isFriendAdded(), "Friend is in the list");
        mainPage.tapAddAFriend();
        Assert.assertTrue(mainPage.isFriendAdded(), "Friend is not in the list");
        mainPage.tapAvatarItem();
        Assert.assertTrue(freindPage.pageIsDisplayed(), "Friend Page is not displayed");
        Assert.assertTrue(freindPage.isFriendAdded(), "Friend is not in the list");

    }

    @AfterMethod
    public void removeFriend(){
        freindPage.tapMenuView();
        freindPage.tapRemoveFriendField();
        Assert.assertTrue(freindPage.isAlertDialogPresent(), "Remove Alert dialog is not present");
        freindPage.tapRemoveFriend();
        mainPage.tapAvatarItem();
        Assert.assertFalse(freindPage.isFriendAdded(), "Friend is in the list");
    }
}