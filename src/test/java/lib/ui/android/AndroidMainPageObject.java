package lib.ui.android;

import lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMainPageObject extends MainPageObject {
    public AndroidMainPageObject(RemoteWebDriver driver) {
        super(driver);
    }
     static{
            MY_LIST = "xpath://android.widget.FrameLayout[@content-desc=\"My lists\"]";
            SKIP = "xpath://*[contains(@text,'SKIP')]";
            OPEN_NAVIGATION = "//label[text()='Open main menu']";
            SEARCH_INPUT_FIELD = "xpath://*[contains(@text,'Search Wikipedia')]";
     }
}
