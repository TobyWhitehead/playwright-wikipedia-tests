package base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;

public class Hooks {

    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp() {
        String browserName = System.getProperty("browser", "chromium");
        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );
        testContext.init(browserName, headless);

        BrowserContext context = testContext.getBrowserContext();
    }

    @After
    public void tearDown(Scenario scenario) {
        BrowserContext context = testContext.getBrowserContext();
        Page page = testContext.getPage();

        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        if (scenario.isFailed()) {
            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "Failure screenshot");

            String traceName = scenario.getName()
                    .replaceAll("[^a-zA-Z0-9]", "_");

            context.tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get("target/traces/" + traceName + ".zip"))
            );
        } else {
            context.tracing().stop();
        }

        testContext.tearDown();
    }
}