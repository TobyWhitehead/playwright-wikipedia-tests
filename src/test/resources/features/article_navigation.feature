Feature: Article navigation

  Scenario: Navigate from an article to a related article
    Given I open the "Playwright" article
    When I click the first internal article link
    Then I should be navigated to a different article