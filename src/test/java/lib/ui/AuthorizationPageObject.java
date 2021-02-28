package lib.ui;

import lib.ui.factories.MainPageObjectFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    private static final String
    LOGIN_BUTTON = "xpath://a[text()='Log in']",
    LOGIN_INPUT = "xpath://input[@name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "css:button#wpLoginAttempt"; //xpath://div[@class = 'mw-input mw-htmlform-nolabel']/button[@type='submit'];

    public void initLogin(){
        waitElementPresentBy(LOGIN_BUTTON).click();
    }
    public void typeLogin(String login){
        waitElementPresentBy(LOGIN_INPUT).sendKeys(login);
    }
    public void typePassword(String password){
        waitElementPresentBy(PASSWORD_INPUT).sendKeys(password);
    }
    public void submitLogIn(){
        waitElementPresentBy(SUBMIT_BUTTON).click();
    }
    public void loginToWiki(String login, String password){
        initLogin();
        typeLogin(login);
        typePassword(password);
        submitLogIn();
    }
    public void generalLogin(String login, String password){
        MainPageObject mainPage =MainPageObjectFactory.get(driver);
        mainPage.openNavigation();
        mainPage.clickLoginFromMainMenu();
        typeLogin(login);
        typePassword(password);
        submitLogIn();

    }
}
