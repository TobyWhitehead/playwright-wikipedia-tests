package base;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static Page initBrowser(boolean headless) {
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

    public static Page getPage() {
        return page;
    }

    public static void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}