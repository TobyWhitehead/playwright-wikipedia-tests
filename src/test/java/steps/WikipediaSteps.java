package steps;

import base.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ArticlePage;
import pages.HomePage;

public class WikipediaSteps {

    private final TestContext testContext;
    private HomePage homePage;
    private ArticlePage articlePage;
    private String previousHeading;

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
        homePage.getSearchBar().searchFor(term);
    }

    @Then("the article heading should be {string}")
    public void theArticleHeadingShouldBe(String heading) {
        articlePage = new ArticlePage(testContext.getPage());
        articlePage.verifyHeading(heading);
    }

    @Given("I open the {string} article")
    public void iOpenTheArticle(String article) {
        homePage = new HomePage(testContext.getPage());
        homePage.navigate();
        homePage.getSearchBar().searchFor(article);
    }

    @When("I click the first internal article link")
    public void iClickTheFirstInternalArticleLink() {
        articlePage = new ArticlePage(testContext.getPage());
        previousHeading = articlePage.getHeadingText();
        articlePage.clickFirstInternalLink();
    }

    @Then("I should be navigated to a different article")
    public void iShouldBeNavigatedToADifferentArticle() {
        articlePage.verifyArticleChanged(previousHeading);
    }
}