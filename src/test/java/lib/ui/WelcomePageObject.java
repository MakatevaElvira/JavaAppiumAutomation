package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class WelcomePageObject extends MainPageObject{
    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static  String
            STEP_LEARN_MORE_ABOUT_WIKIPEDIA ,
            NEXT_BUTTON ,
            GET_STARTED_BUTTON ,
            STEP_NEW_WAYS ,
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES ,
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED;

    public void waitLearnMoreWikipediaLink(){
        waitElementPresentBy((STEP_LEARN_MORE_ABOUT_WIKIPEDIA));
    }
    public void clickNextButton(){
        waitElementPresentBy((NEXT_BUTTON)).click();
    }
    public void clickGetStartedButton(){
        waitElementPresentBy((GET_STARTED_BUTTON)).click();
    }
    public void waitNewWaysLink(){
        waitElementPresentBy((STEP_NEW_WAYS));
    }
    public void waitAddOrEditLanguagesLink(){
        waitElementPresentBy((STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES));
    }
    public void waitLearnMoreDataCollectedLink(){
        waitElementPresentBy((STEP_LEARN_MORE_ABOUT_DATA_COLLECTED));
    }
}
