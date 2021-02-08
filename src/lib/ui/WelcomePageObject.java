package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{
    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            STEP_LEARN_MORE_ABOUT_WIKIPEDIA = "Learn more about Wikipedia",
            NEXT_BUTTON = "Next",
            GET_STARTED_BUTTON = "Get started",
            STEP_NEW_WAYS = "New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES = "Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "Learn more about data collected";

    public void waitLearnMoreWikipediaLink(){
        waitElementPresentBy(By.id(STEP_LEARN_MORE_ABOUT_WIKIPEDIA));
    }
    public void clickNextButton(){
        waitElementPresentBy(By.id(NEXT_BUTTON)).click();
    }
    public void clickGetStartedButton(){
        waitElementPresentBy(By.id(GET_STARTED_BUTTON)).click();
    }
    public void waitNewWaysLink(){
        waitElementPresentBy(By.id(STEP_NEW_WAYS));
    }
    public void waitAddOrEditLanguagesLink(){
        waitElementPresentBy(By.id(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES));
    }
    public void waitLearnMoreDataCollectedLink(){
        waitElementPresentBy(By.id(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED));
    }
}
