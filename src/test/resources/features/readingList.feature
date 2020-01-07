Feature: reading list functionality end to end

  Scenario: Add new book to log user
    Given I have log to the read list portal as <any>
    When I add the book with the following specifications
    Then I should see the book added to my books list