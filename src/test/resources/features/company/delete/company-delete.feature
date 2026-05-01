Feature: Company Delete

  Background:
    Given I am authenticated

  Scenario: Delete an existing company

    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "name": "Neo Energia Companhia de Energia S.A.",
      "active": true
    }
    """

    When I DELETE "/company/019ca71b-3183-7a8b-8f71-e44a327a7846"
    Then the response status code should be 200
    Then the table "concessionaires" should have 0 records

  Scenario: Delete a company that does not exist

    When I DELETE "/company/019ca71b-3183-7a8b-8f71-e44a327a7999"
    Then the response status code should be 200

