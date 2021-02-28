package lib.ui.factories;

import lib.Platform;
import lib.ui.MainPageObject;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidMainPageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.ios.IOsMainPageObject;
import lib.ui.ios.IOsMyListPageObject;
import lib.ui.mobileWeb.MWMainPageObject;
import lib.ui.mobileWeb.MWMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPageObjectFactory {
    public static MainPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMainPageObject(driver);
        } else if (Platform.getInstance().isMW()){
            return new MWMainPageObject(driver);
        }else {
            return new IOsMainPageObject(driver);
        }
    }
}
