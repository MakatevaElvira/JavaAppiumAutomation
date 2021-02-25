package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidWelcomePageObject extends WelcomePageObject {
    public AndroidWelcomePageObject (RemoteWebDriver driver){
        super(driver);
    }
     static {
         STEP_LEARN_MORE_ABOUT_WIKIPEDIA = "id:Learn more about Wikipedia";
         NEXT_BUTTON = "id:Next";
         GET_STARTED_BUTTON = "id:Get started";
         STEP_NEW_WAYS = "id:New ways to explore";
         STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES = "id:Add or edit preferred languages";
         STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected";
     }
}
