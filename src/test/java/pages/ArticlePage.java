package pages;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class ArticlePage {

    private final Page page;

    public ArticlePage(Page page) {
        this.page = page;
    }

    public void verifyHeading(String expectedHeading) {
        PlaywrightAssertions.assertThat(
                page.getByRole(
                        AriaRole.HEADING,
                        new Page.GetByRoleOptions().setLevel(1)
                )
        ).hasText(expectedHeading);
    }
}