package pages;

import com.microsoft.playwright.Page;
import pages.components.SearchBar;

public class HomePage {

    private final Page page;
    private final SearchBar searchBar;

    public HomePage(Page page) {
        this.page = page;
        this.searchBar = new SearchBar(page);
    }

    public void navigate() {
        page.navigate("https://www.wikipedia.org/");
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }
}