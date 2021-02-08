package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{
    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            STEP_LEARN_MORE_ABOUT_WIKIPEDIA = "id:Learn more about Wikipedia",
            NEXT_BUTTON = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
            STEP_NEW_WAYS = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected";

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
