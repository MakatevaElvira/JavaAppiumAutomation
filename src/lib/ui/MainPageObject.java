package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import static java.time.Duration.ofMillis;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    private static final String
            MY_LIST = "xpath://android.widget.FrameLayout[@content-desc=\"My lists\"]",
            SKIP = "xpath://*[contains(@text,'SKIP')]",
            SEARCH_INPUT_FIELD = "xpath://*[contains(@text,'Search Wikipedia')]";

    public WebElement waitElementPresentBy(String locator) {
        By by = getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.withMessage("No element present by locator: " + locator);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    public Boolean assertElementPresent(String locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 0);
        webDriverWait.withMessage("No element present by locator: " + locator);
        List<WebElement> elements = webDriverWait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(getLocatorByString(locator)));
        if (elements.size() > 0) {
            return true;
        }
        return false;

    }

    public List<WebElement> waitElementsPresentBy(String locator) {
        By by = getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.withMessage("No element present by locator: " + locator);
        return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

    }

    public Boolean waitElementNotPresentBy(String locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.withMessage("No element present by locator: " + locator);
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(getLocatorByString(locator)));

    }

    public boolean assertElementHasText(String locator, String expectedText, String errorMessage) {
        String gettingText = waitElementPresentBy(locator).getAttribute("name");
        System.out.println("expected= " + expectedText);
        System.out.println("getting= " + gettingText);
        if (gettingText.equals(expectedText)) {
            return true;
        }
        System.out.println(errorMessage);
        ;
        return false;
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.width * 0.8);
        int endY = (int) (size.width * 0.2);
        PointOption startPoint = new PointOption().withCoordinates(x, startY);
        PointOption endPoint = new PointOption().withCoordinates(x, endY);
        action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();
    }

    public void swipeQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String by, int maxSwipes) {
        int alreadySwipe = 0;
        while (waitElementsPresentBy(by).size() == 0) { //driver.findElements
            if (alreadySwipe > maxSwipes) {
                waitElementPresentBy(by);
                return;
            }
            swipeQuick();
            ++alreadySwipe;
        }
    }

    public void swipeUpElementToLeft(String by, int timeOfSwipe) {
        WebElement element = waitElementPresentBy(by);
        int leftX = (int) (element.getLocation().getX() * 0.1);
        int rightX = (int) ((leftX + element.getSize().getWidth()) * 0.9);//прибавили к левой точке ширину элемента и получили правую
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();//прибавили к верхней точке высоту элемента и получили нижнюю
        int middleY = (upperY + lowerY) / 2;
        PointOption startPoint = new PointOption().withCoordinates(rightX, middleY);
        PointOption endPoint = new PointOption().withCoordinates(leftX, middleY);
        TouchAction action = new TouchAction(driver);
        action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();

    }

    public String findElementAndGetAttribute(String locator, String attribute) {
        return waitElementPresentBy(locator).getAttribute(attribute);

    }

    public void openMyList() {
        waitElementPresentBy(MY_LIST).click();
    }

    public By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Can`t find locatorWithType= " + locatorWithType);
        }
    }
    public void skipStartInformation() {
        waitElementPresentBy(SKIP).click();
    }
    public WebElement findSearchInputField() {
        return waitElementPresentBy(SEARCH_INPUT_FIELD);
    }
}

