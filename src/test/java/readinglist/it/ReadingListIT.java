package readinglist.it;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import readinglist.ReadListApplication;

import static org.junit.Assert.assertEquals;
import static readinglist.webdriverfuctions.PageFunctions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadingListIT {


    @Value("${local.server.port}")
    private int port;

    @After
    public void tearDown() {
        quitDriver();
    }

    @Test
    public void shouldAddBookToReadingList() {
        String url = "http://localhost:" + port;
        goToPage(url);

        logToAlvaroUser();
        assertEquals("You have no books in your book list", findAnyElementById("ListOwner").getText());

        findAnyElementByName("title").sendKeys("BOOK TITLE");
        findAnyElementByName("author").sendKeys("BOOK AUTHOR");
        findAnyElementByName("isbn").sendKeys("BOOK ISBN");
        findAnyElementByName("description").sendKeys("BOOK DESCRIPTION");

        findAnyElementById("submit").click();

        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: BOOK ISBN)", findAnyElementByCssSelector("dt.bookHeadline").getText());
        assertEquals("BOOK DESCRIPTION", findAnyElementByCssSelector("dd.bookDescription").getText());
    }

    private void logToAlvaroUser() {
        findAnyElementByName("username").sendKeys("alvaro");
        findAnyElementByName("password").sendKeys("password");

        findAnyElementByName("submit").click();
    }
}
