package base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class TestContext {

    private static final ThreadLocal<PlaywrightFactory> factory = new ThreadLocal<>();

    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public void init(boolean headless) {
        PlaywrightFactory playwrightFactory = new PlaywrightFactory();
        factory.set(playwrightFactory);
        page.set(playwrightFactory.initBrowser(headless));
    }

    public Page getPage() {
        return page.get();
    }

    public BrowserContext getBrowserContext() {
        return factory.get().getContext();
    }

    public void tearDown() {
        if (factory.get() != null) {
            factory.get().tearDown();
        }
        page.remove();
        factory.remove();
    }
}