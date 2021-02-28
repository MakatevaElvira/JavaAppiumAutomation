package lib.ui.ios;

import lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOsMainPageObject extends MainPageObject {
    public IOsMainPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    static {
        MY_LIST = "xpath://*[@content-desc=\"My lists\"]";
        SKIP = "xpath://*[contains(@text,'SKIP')]";
        OPEN_NAVIGATION = "//label[text()='Open main menu']";
        SEARCH_INPUT_FIELD = "xpath://*[contains(@text,'Search Wikipedia')]";

    }

}
