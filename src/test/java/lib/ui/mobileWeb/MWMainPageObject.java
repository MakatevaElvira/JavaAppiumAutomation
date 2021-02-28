package lib.ui.mobileWeb;

import lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMainPageObject extends MainPageObject {
    public MWMainPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    static {
        MY_LIST = "xpath://span[text()='Watchlist']";
        OPEN_NAVIGATION = "xpath://label[text()='Open main menu']";
        SEARCH_INPUT_FIELD = "xpath://*[contains(@text,'Search Wikipedia')]";
        LOG_IN_BUTTON = "xpath://span[text()='Log in']";
    }
}
