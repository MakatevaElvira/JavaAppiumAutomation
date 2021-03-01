package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static lib.ui.MyListPageObject.getArticleByText;


abstract public class SearchPageObject extends MainPageObject {
    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT,
            SEARCH_RESULT_TITLE_BY_TPL,
            SEARCH_RESULT_BY_TPL,
            SEARCH_CANCEL_BUTTON,
            FIRST_ARTICLE_TITLE,
            RETURN_TO_MAIN,
            SEARCH_EMPTY_RESULT;

    //
    @Step("Инициировать Поиск")
    public void initSearchInput() {
        waitElementPresentBy((SEARCH_INIT_ELEMENT)).click();
    }
    @Step("Ввести значение поиска")
    public void typeSearchValue(String toFind) {
        waitElementPresentBy((SEARCH_INPUT)).sendKeys(toFind);
        screenshot(takeScreenShot("searchResultPage"));
       // driver.getKeyboard().sendKeys(Keys.ENTER);//проблема!!!
      //  driver.pressKeyCode(new KeyEvent(AndroidKey.ENTER));

    }
    @Step("Дождаться отображения результатов поиска")
    public void waitSearchResult() {
        waitElementPresentBy((SEARCH_RESULT));
    }
    @Step("Дождаться отсутствия отображения элемента поиска")
    public Boolean waitSearchResultNotPresent() {
        return waitElementNotPresentBy((SEARCH_RESULT));
    }
    @Step("Нажать кнопку отмены поиска")
    public void tabCancelSearchButton() {
        waitElementPresentBy((SEARCH_CANCEL_BUTTON)).click();
    }
    @Step("Открыть статью по тексту= {titel}")
    public void openArticleByTitle(String titel) {
        waitElementPresentBy(getResultSearchTitleByText(titel)).click();
        //waitElementPresentBy(("xpath://*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='" + titel + "']")).click();
    }
    @Step("Открыть первую статью ")
    public void openFirstArticle() {
        waitElementPresentBy((FIRST_ARTICLE_TITLE)).click();
    }
    @Step("Перейти к главной странице")
    public void returnToMainPage() {
        if ((Platform.getInstance().isAndroid()) || Platform.getInstance().isIOs()){
            waitElementPresentBy((RETURN_TO_MAIN)).click();
        }else {
            System.out.println("Method returnToMainPage has nothing for platform= "+Platform.getInstance().getPlatformVar());
        }
    }

    public Boolean waitForElementByTitleAndDescriptionOld(String expectedTitle, String expectedDescription) {
        List<WebElement> elements = waitElementsPresentBy(("xpath://android.view.ViewGroup"));
        WebElement element = driver.findElement(By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title']"));
        System.out.println("Element=== " + element.getText());
        WebElement e2 = driver.findElement(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title']"));
        System.out.println("Element second=== " + e2.getText());

        int mySize = driver.findElements(By.xpath("//android.view.ViewGroup/android.widget.TextView[contains(@text,'" + expectedTitle + "')]/.." +
                "/android.widget.TextView[contains(@text,'" + expectedDescription + "')]")).size();
        System.out.println("mySize = " + mySize);

        int sizeResulr = elements.size();
        for (int i = 0; i < sizeResulr; i++) {
            // elements = waitElementsPresentBy(By.xpath("//android.view.ViewGroup"));
            elements = driver.findElements(By.xpath("//android.view.ViewGroup"));
            //WebElement element1 = driver.findElement(By.xpath("//android.view.ViewGroup"));
            // int size = element1.findElements(By.xpath(".//*[@resource-id='org.wikipedia.beta:id/page_list_item_title']")).size();
            String titleName = elements.get(i).findElements(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title']")).get(0).getText();

            if (titleName.contains(expectedTitle)) {
                String descriptionName = elements.get(i).findElements(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_description']")).get(0).getText();
                if (descriptionName.contains(expectedDescription)) {
                    return true;
                }
                Assert.fail("No elements with title= " + expectedTitle);
                return false;
            }
        }
        Assert.fail("No elements with title= " + expectedTitle + " and description = " + expectedDescription);
        ;
        return false;
    }


    /*TEMPLATES METHODS */
    @Step("Получить результат поиска по тексту= {text}")
    public static String getResultSearchByText(String text) {
        return SEARCH_RESULT_BY_TPL.replace("{SUBSTRING}", text);
    }
    @Step("Получить заголовок результат поиска по тексту= {text}")
    public static String getResultSearchTitleByText(String text) {
        return SEARCH_RESULT_TITLE_BY_TPL.replace("{SUBSTRING}", text);
    }

    /*TEMPLATES METHODS */

    @Step("Дождаться отображения статьи с заголовком  =  {expectedTitle} и  Description= {expectedDescription}")
    public Boolean waitForElementByTitleAndDescription(String expectedTitle, String expectedDescription) {
        waitElementPresentBy(("xpath://android.view.ViewGroup/android.widget.TextView[contains(@text,'" + expectedTitle + "')]/.." +
                "/android.widget.TextView[contains(@text,'" + expectedDescription + "')]"));
        return true;
    }
    @Step("Получить размер списка элементов с текстовм = {text}")
    public  int searchCountNew(String text) {
        List<WebElement> results = waitElementsPresentBy(getResultSearchTitleByText(text));
        return results.size();
    }
    @Step("Получить  список элементов с текстовм = {text}")
    public  List<WebElement> getSearchResultsList(String text) {
        List<WebElement> results = waitElementsPresentBy(getResultSearchTitleByText(text));
        return results;
    }
    public void searchCount() {
        List<WebElement> results = waitElementsPresentBy(("xpath://android.view.ViewGroup"));
        List<String> resultsTitles = new ArrayList<>();
        int resultSize = results.size();
        System.out.println("resultSize = " + resultSize);
        if (resultSize > 2) {
            for (int i = 0; i < resultSize; i++) {
                results = waitElementsPresentBy(("xpath://android.view.ViewGroup/android.widget.TextView"));
                resultsTitles.add(results.get(i).getText());
                driver.findElement(By.xpath("//android.view.ViewGroup/android.widget.TextView[contains(@text,'" + results.get(i).getText() + "')]"));
            }
        } else {
            System.out.println("Results size < 3");
            Assert.fail();
            return;
        }
    }
}
