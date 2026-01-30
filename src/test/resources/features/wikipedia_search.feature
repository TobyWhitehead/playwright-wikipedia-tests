Feature: Wikipedia search

  Scenario: Search for an article
    Given I am on the Wikipedia home page
    When I search for "Playwright"
    Then the article heading should be "Playwright"