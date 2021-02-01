package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject{
    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }

    public String getArticleTitle(String title){
        return waitElementPresentBy(By.xpath("//android.view.View[@text='"+title+"']")).getText();
    }
}
