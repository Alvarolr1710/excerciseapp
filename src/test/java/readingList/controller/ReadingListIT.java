package readingList.controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import readingList.ReadListApplication;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadingListIT {

    private static WebDriver browser;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    @Test
    public void shouldAddBookToReadingList() {
        String url = "http://localhost:" + port;
        browser.get(url);

        logToAlvaroUser();
        assertEquals("You have no books in your book list", browser.findElement(id("ListOwner")).getText());

        browser.findElement(name("title")).sendKeys("BOOK TITLE");
        browser.findElement(name("author")).sendKeys("BOOK AUTHOR");
        browser.findElement(name("isbn")).sendKeys("BOOK ISBN");
        browser.findElement(name("description")).sendKeys("BOOK DESCRIPTION");

        browser.findElement(id("submit")).click();

        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: BOOK ISBN)", browser.findElement(cssSelector("dt.bookHeadline")).getText());
        assertEquals("BOOK DESCRIPTION", browser.findElement(cssSelector("dd.bookDescription")).getText());
    }

    private void logToAlvaroUser() {
        browser.findElement(name("username")).sendKeys("alvaro");
        browser.findElement(name("password")).sendKeys("password");

        browser.findElement(name("submit")).click();


    }
}
