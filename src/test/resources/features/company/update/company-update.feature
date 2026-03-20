Feature: Company

  Background:
    Given I am authenticated

  Scenario: Update a company

    Given the following company exists in the database:
    """
    {
       "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
       "name": "neo energia"
    }
    """

    When I PUT the payload to "/company" with body:
    """
    {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
        "name": "Neo Energia Companhia de Energia S.A."
    }
    """
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
       "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
       "name": "Neo Energia Companhia de Energia S.A."
    }
    """
    Then the table "company" should have 1 records

