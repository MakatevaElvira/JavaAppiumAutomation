package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.ios.IOsMyListPageObject;
import lib.ui.mobileWeb.MWMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {
    public static MyListPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        } else if (Platform.getInstance().isMW()){
            return new MWMyListPageObject(driver);
        }else {
            return new IOsMyListPageObject(driver);
        }
    }
}
