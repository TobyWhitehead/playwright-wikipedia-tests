package pages.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchBar {

    private final Page page;

    private final Locator searchInput;
    //private final Locator firstResult;

    public SearchBar(Page page) {
        this.page = page;
        this.searchInput = page.getByLabel("Search Wikipedia");
        //this.firstResult = page.locator(".cdx-menu-item__content").first();
    }

    public void searchFor(String term) {
        searchInput.fill(term);
        page.keyboard().press("Enter");
        //firstResult.click();
    }
}