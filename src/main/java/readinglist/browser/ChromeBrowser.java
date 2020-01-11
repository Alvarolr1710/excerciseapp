package readinglist.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeBrowser {

    private static WebDriver chromeBrowser;

    private ChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        chromeBrowser = new ChromeDriver();
        chromeBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getChromeWebDriver() {
        if (chromeBrowser == null) {
            new ChromeBrowser();
        }
        return chromeBrowser;
    }
}
