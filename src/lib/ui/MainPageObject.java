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

import static java.time.Duration.ofMillis;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver){
        this.driver=driver;
    }
    public WebElement waitElementPresentBy(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public Boolean assertElementPresent(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,0);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        List<WebElement> elements= webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        if (elements.size()>0){
            return true;
        }
        return false;

    }
    public List <WebElement> waitElementsPresentBy(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

    }
    public Boolean waitElementNotPresentBy(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }
    public boolean assertElementHasText(By locator, String expectedText,String errorMessage){
        String gettingText = waitElementPresentBy(locator).getAttribute("name");
        System.out.println("expected= "+expectedText);
        System.out.println("getting= " + gettingText);
        if (gettingText.equals(expectedText)){
            return true;
        }
        System.out.println(errorMessage);;
        return false;
    }

    public void swipeUp(int timeOfSwipe)  {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int startY = (int)(size.width * 0.8);
        int endY = (int)(size.width * 0.2);
        PointOption startPoint = new PointOption().withCoordinates(x,startY);
        PointOption endPoint = new PointOption().withCoordinates(x,endY);
        action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();
    }
    public void swipeQuick() {
        swipeUp(200);
    }
    public void swipeUpToFindElement(By by, int maxSwipes) {
        int alreadySwipe = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipe > maxSwipes){
                waitElementPresentBy(by);
                return;
            }
            swipeQuick();
            ++alreadySwipe;
        }
    }
    public void swipeUpElementToLeft(By by, int timeOfSwipe) {
        WebElement element = waitElementPresentBy(by);
        int leftX = (int)(element.getLocation().getX()* 0.1);
        int rightX = (int)( (leftX + element.getSize().getWidth()) * 0.9);//прибавили к левой точке ширину элемента и получили правую
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();//прибавили к верхней точке высоту элемента и получили нижнюю
        int middleY = (upperY + lowerY )/2;
        PointOption startPoint = new PointOption().withCoordinates(rightX, middleY);
        PointOption endPoint = new PointOption().withCoordinates(leftX,middleY);
        TouchAction action = new TouchAction(driver);
        action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();

    }

    public String findElementAndGetAttribute( By locator, String attribute){
        return waitElementPresentBy(locator).getAttribute(attribute);

    }
}
