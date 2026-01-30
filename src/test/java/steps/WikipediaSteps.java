package steps;

import base.TestContext;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class WikipediaSteps {

    private final TestContext testContext;
    private HomePage homePage;

    public WikipediaSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("I am on the Wikipedia home page")
    public void iAmOnTheWikipediaHomePage() {
        homePage = new HomePage(testContext.getPage());
        homePage.navigate();
    }

    @When("I search for {string}")
    public void iSearchFor(String term) {
        homePage.searchFor(term);
    }

    @Then("the article heading should be {string}")
    public void theArticleHeadingShouldBe(String heading) {
        PlaywrightAssertions.assertThat(
                testContext.getPage().locator("h1")
                ).hasText(heading);
    }
}