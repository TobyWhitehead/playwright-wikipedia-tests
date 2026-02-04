package base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class TestContext {

    private PlaywrightFactory playwrightFactory;
    private Page page;

    public void init(String browserName, boolean headless) {
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser(browserName, headless);
    }

    public Page getPage() {
        return page;
    }

    public BrowserContext getBrowserContext() {
        return playwrightFactory.getContext();
    }

    public void tearDown() {
        if (playwrightFactory != null) {
            playwrightFactory.tearDown();
        }
    }
}
