Feature: Address

  Background:
    Given I am authenticated

  Scenario: Create a address with all fields

    When I GET "/address"
    Then the response status code should be 200
    Then the response body should contain:
    """
    []
    """
