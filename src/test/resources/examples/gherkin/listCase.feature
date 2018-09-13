Feature: Serve coffee
  In order to earn money
  Customers should be able to
  buy coffee at all times

  Scenario: Buy last coffee
    Given there are 1 coffees left in the machine
    And I have deposited 1 dollar
    When I press the coffee button
    Then I should be served a coffee
  Scenario: Buy two coffees
    Given there are more than 1 coffees left in the machine
    And I have deposited 2 dollar
    When I press the coffee button twice
    Then I should be served a two coffee