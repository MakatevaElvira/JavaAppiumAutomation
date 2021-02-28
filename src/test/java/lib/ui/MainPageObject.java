package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import lib.ui.factories.MyListPageObjectFactory;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import static java.time.Duration.ofMillis;

abstract public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    protected static String
            MY_LIST,
            SKIP,
            OPEN_NAVIGATION,
            SEARCH_INPUT_FIELD,
            LOG_IN_BUTTON;

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

    public int getAmountOfElements(String locator) {
        By by = getLocatorByString(locator);
        int size = driver.findElements(by).size();
        System.out.println("Size = "+ size);
        return driver.findElements(by).size();

    }

    public Boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }
    public Boolean isElementVisible(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            TouchAction action = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int startY = (int) (size.width * 0.8);
            int endY = (int) (size.width * 0.2);
            PointOption startPoint = new PointOption().withCoordinates(x, startY);
            PointOption endPoint = new PointOption().withCoordinates(x, endY);
            action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();

        } else {
            System.out.println("SwipeUp has not for platform= " + Platform.getInstance().getPlatformVar());
        }
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
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitElementPresentBy(by);
            int leftX = (int) (element.getLocation().getX() * 0.1);
            int rightX = (int) ((leftX + element.getSize().getWidth()) * 0.9);//прибавили к левой точке ширину элемента и получили правую
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();//прибавили к верхней точке высоту элемента и получили нижнюю
            int middleY = (upperY + lowerY) / 2;
            PointOption startPoint = new PointOption().withCoordinates(rightX, middleY);
            PointOption endPoint = new PointOption().withCoordinates(leftX, middleY);
            TouchAction action = new TouchAction(driver);
            action.press(startPoint);
            action.waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe)));
            if (Platform.getInstance().isAndroid()) {
                action.moveTo(endPoint);
            } else {
                int offsetX = (-1 * element.getSize().getWidth());
                PointOption offsetPoint = new PointOption().withCoordinates(offsetX, 0);
                action.moveTo(offsetPoint);
            }
            action.release().perform();
            if (Platform.getInstance().isIOs()) {
                clickToElementsUpperCorner(by);
            }
        } else {
            System.out.println("SwipeUpElementToLeft has not for platform= " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickToElementsUpperCorner(String locator) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitElementPresentBy(locator + "/..");
            int rightX = (element.getLocation().getX());//прибавили к левой точке ширину элемента и получили правую
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();//прибавили к верхней точке высоту элемента и получили нижнюю
            int middleY = (upperY + lowerY) / 2;
            int width = element.getSize().getWidth();
            int pointToClickX = (rightX + width) - 3;
            PointOption clickPoint = new PointOption().withCoordinates(pointToClickX, middleY);
            TouchAction action = new TouchAction(driver);
            action.tap(clickPoint).perform();
        } else {
            System.out.println("ClickToElementsUpperCorner has not for platform= " + Platform.getInstance().getPlatformVar());
        }
    }

    public String findElementAndGetAttribute(String locator, String attribute) {
        return waitElementPresentBy(locator).getAttribute(attribute);
    }

    public void openMyList() {
        if (Platform.getInstance().isMW()) {
            tryClickElementWithFewAttempts(MY_LIST, 5);
        }
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
        } else if (byType.equals("css")) {
            return By.cssSelector(locator);
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

    public void swipeUpElementAppear(String locator, int maxSwipe) {
        int alreadySwiped = 0;
        while (!isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipe) {
                Assert.assertTrue(isElementLocatedOnTheScreen(locator));
            }
            swipeQuick();
            ++alreadySwiped;
        }
    }

    public Boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = waitElementPresentBy(locator).getLocation().getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object jsResult = JSExecutor.executeScript("return window.pageYOffset");
            elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();//получить длину экрана
        return elementLocationByY < screenSizeByY;
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        } else {
            System.out.println("Scroll not work for platform= " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebElementTillNotVisible(String locator, int maxSwipes) {
        int alreadySwipe = 0;
        WebElement element = waitElementPresentBy(locator);

        if (isElementPresent(locator))
        while (!isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++alreadySwipe;
            if (alreadySwipe > maxSwipes) {
                System.out.println("Element with locator= " + locator + " is not LocatedOnTheScreen");
                return;
            }
        }
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        } else {
            System.out.println("Scroll not work for platform= " + Platform.getInstance().getPlatformVar());
        }
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            waitElementPresentBy(OPEN_NAVIGATION).click();
        } else {
            System.out.println("Method openNavigation doesn't work for platform= " + Platform.getInstance().getPlatformVar());
        }
    }

    public void tryClickElementWithFewAttempts(String locator, int attemptsAmount) {
        int alreadyAttempts = 0;
        boolean needMoreAttempts = true;
        while (needMoreAttempts) {
            try {
                waitElementPresentBy(locator).click();
                needMoreAttempts = false;
            } catch (Exception exception) {
                if (alreadyAttempts > attemptsAmount) {
                    waitElementPresentBy(locator).click();
                }
            }
        }
        ++alreadyAttempts;

    }

    public void openMySavedArticles() {
        if (Platform.getInstance().isMW()) {
            waitElementPresentBy(MY_LIST).click();

        } else {
            //Открыть мои статьи на гл экране
            openMyList();
            //Открыть Сохраненные
            MyListPageObjectFactory.get(driver).openSaved();
        }
    }
    public void clickLoginFromMainMenu(){
        waitElementPresentBy(LOG_IN_BUTTON).click();
    }
}