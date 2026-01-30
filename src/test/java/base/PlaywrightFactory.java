package base;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page initBrowser(boolean headless) {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
        );

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1280, 800)
        );

        page = context.newPage();
        return page;
    }

    public BrowserContext getContext() {
        return context;
    }

    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}