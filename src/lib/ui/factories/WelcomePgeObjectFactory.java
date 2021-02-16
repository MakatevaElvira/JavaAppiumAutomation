package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.android.AndroidWelcomePageObject;
import lib.ui.ios.IOsSearchPageObject;
import lib.ui.ios.IOsWelcomePageObject;

public class WelcomePgeObjectFactory {
    public static WelcomePageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidWelcomePageObject(driver);
        } else {
            return new IOsWelcomePageObject(driver);
        }
    }
}
