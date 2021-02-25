package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOsArticlePageObject;
import lib.ui.ios.IOsSearchPageObject;
import lib.ui.mobileWeb.MWArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        } else if(Platform.getInstance().isMW()){
            return new MWArticlePageObject(driver);
        }else {
            return new IOsArticlePageObject(driver);
        }
    }
}
