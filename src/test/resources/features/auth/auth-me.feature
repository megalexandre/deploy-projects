Feature: Authentication - Get Current User

  Background:
    Given I am authenticated

  Scenario: Get current authenticated user information

    When I GET "/auth/me"
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "userId": "${json-unit.any-string}",
      "email": "test@mail.com",
      "authorities": [
        {
          "authority": "ROLE_USER"
        }
      ]
    }
    """

  Scenario: Fail to get user information when not authenticated
    Given I am not authenticated
    When I GET "/auth/me"
    Then the response status code should be 403
