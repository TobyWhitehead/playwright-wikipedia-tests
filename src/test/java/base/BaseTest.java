package base;

import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseTest {

    protected static Page page;

    @Before
    public void setUp() {
        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );

        page = PlaywrightFactory.initBrowser(headless);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "Failure screenshot");
        }
        PlaywrightFactory.tearDown();
    }
}