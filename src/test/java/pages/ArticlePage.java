package pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class ArticlePage {

    private final Page page;

    // locators
    private final Locator articleHeading;
    private final Locator internalLinks;

    public ArticlePage(Page page) {
        this.page = page;
        this.articleHeading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setLevel(1)
        );
        this.internalLinks = page.locator("#bodyContent a[href^=\"/wiki/\"]");
    }

    public String getHeadingText() {
        return articleHeading.textContent();
    }

    public void verifyHeading(String expectedHeading) {
        PlaywrightAssertions.assertThat(articleHeading)
                .hasText(expectedHeading);
    }

    public void clickFirstInternalLink() {
        internalLinks.first().click();
    }

    public void verifyArticleChanged(String previousHeading) {
        PlaywrightAssertions.assertThat(articleHeading)
                .not().hasText(previousHeading);
    }
}