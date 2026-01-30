package pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchFor(String text) {
        page.getByRole(
                com.microsoft.playwright.options.AriaRole.TEXTBOX)
                .fill(text);

        page.keyboard().press("Enter");
    }
}