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
        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );
        testContext.init(headless);

        BrowserContext context = testContext.getBrowserContext();
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
                );
    }

    @After
    public void tearDown(Scenario scenario) {
        BrowserContext context = testContext.getBrowserContext();
        Page page = testContext.getPage();

        if (scenario.isFailed()) {
            String traceName = scenario.getName()
                    .replaceAll("[^a-zA-Z0-9]", "_");

            context.tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get("target/traces/" + traceName + ".zip"))
            );

            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "Failure screenshot");
        } else {
            context.tracing().stop();
        }

        testContext.tearDown();
    }
}