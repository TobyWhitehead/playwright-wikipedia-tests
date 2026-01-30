package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    // locators
    private final Locator searchInput;

    public HomePage(Page page) {
        this.page = page;
        this.searchInput = page.getByLabel("Search Wikipedia");
    }

    public void navigate() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchFor(String text) {
        searchInput.fill(text);
        page.keyboard().press("Enter");
    }
}