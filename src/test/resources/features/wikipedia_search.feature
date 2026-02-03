Feature: Wikipedia search

  @smoke @search
  Scenario Outline: Search for an article
    Given I am on the Wikipedia home page
    When I search for "<term>"
    Then the article heading should be "<heading>"

    Examples:
      | term       | heading    |
      | Playwright | Playwright |
      | Selenium   | Selenium   |
      | Java       | Java       |