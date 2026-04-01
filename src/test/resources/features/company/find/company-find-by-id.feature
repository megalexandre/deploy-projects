Feature: Company Find By Id

  Background:
    Given I am authenticated

  Scenario: Get by id a company that exists
    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "name": "Neo Energia Companhia de Energia S.A."
    }
    """
    When I GET "/company/019ca71b-3183-7a8b-8f71-e44a327a7846"
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "name": "Neo Energia Companhia de Energia S.A."
    }
    """

  Scenario: Get by id a company that does not exist
    When I GET "/company/019ca71b-3183-7a8b-8f71-e44a327a7999"
    Then the response status code should be 404

