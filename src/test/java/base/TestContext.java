package base;

import com.microsoft.playwright.Page;

public class TestContext {

    private PlaywrightFactory playwrightFactory;
    private Page page;

    public void init(boolean headless) {
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser(headless);
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void tearDown() {
        if (playwrightFactory != null) {
            playwrightFactory.tearDown();
        }
    }
}