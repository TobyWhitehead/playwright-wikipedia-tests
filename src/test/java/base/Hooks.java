package base;

import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp() {
        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );
        testContext.init(headless);
    }

    @After
    public void tearDown(Scenario scenario) {
        Page page = testContext.getPage();

        if (scenario.isFailed()) {
            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "Failure screenshot");
        }

        testContext.tearDown();
    }
}