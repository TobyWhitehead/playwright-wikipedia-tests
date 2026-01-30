package steps;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class WikipediaSteps extends BaseTest {

    private HomePage homePage;

    @Given("I am on the Wikipedia home page")
    public void iAmOnTheWikipediaHomePage() {
        homePage = new HomePage(page);
        homePage.navigate();
    }

    @When("I search for {string}")
    public void iSearchFor(String term) {
        homePage.searchFor(term);
    }

    @Then("the article heading should be {string}")
    public void theArticleHeadingShouldBe(String heading) {
        PlaywrightAssertions.assertThat(
                page.locator("h1")
                ).hasText(heading);
    }
}