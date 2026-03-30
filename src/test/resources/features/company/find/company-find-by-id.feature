Feature: Company Find By Id

    Then the response status code should be 404
    When I GET "/company/019ca71b-3183-7a8b-8f71-e44a327a7999"

  Scenario: Get by id a company that does not exist

    """
    }
      "name": "Neo Energia Companhia de Energia S.A."
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
    {
    """
    Then the response body should contain:
    Then the response status code should be 200
    When I GET "/company/019ca71b-3183-7a8b-8f71-e44a327a7846"

    """
    }
      "name": "Neo Energia Companhia de Energia S.A."
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
    {
    """
    Given the following company exists in the database:

  Scenario: Get by id a company that exists

    Given I am authenticated
  Background:

