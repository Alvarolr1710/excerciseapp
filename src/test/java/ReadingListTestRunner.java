import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "readinglist.glue",
        plugin = {"pretty", "json:report/cucumber.json"},
        strict = true,
        features = "src/test/resources/features/",
        stepNotifications = true)

public class ReadingListTestRunner {
}