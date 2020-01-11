package readinglist.webdriverfuctions;


import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.*;
import static readinglist.browser.ChromeBrowser.getChromeWebDriver;

public class PageFunctions {


    public static WebElement findAnyElementByName(String name) {
        return getChromeWebDriver().findElement(name(name));
    }

    public static WebElement findAnyElementByCssSelector(String cssSelector) {
        return getChromeWebDriver().findElement(cssSelector(cssSelector));
    }

    public static WebElement findAnyElementById(String id) {
        return getChromeWebDriver().findElement(id(id));
    }

    public static void goToPage(String url) {
        getChromeWebDriver().get(url);
    }

    public static void quitDriver() {
        getChromeWebDriver().quit();
    }


}
