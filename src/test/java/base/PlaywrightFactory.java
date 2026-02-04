package base;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    public Page initBrowser(String  browserName, boolean headless) {
        playwright = Playwright.create();

        BrowserType browserType = switch (browserName.toLowerCase()) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };

        browser = browserType.launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
        );

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1280, 800)
        );

        return context.newPage();
    }

    public BrowserContext getContext() {
        return context;
    }

    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();

        context = null;
        browser = null;
        playwright = null;
    }
}