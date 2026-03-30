Feature: Company Find All

  Background:
    Given I am authenticated

  Scenario: Get all companies when there are no companies

    When I GET "/company"
    Then the response status code should be 200
    Then the response body should contain:
    """
    []
    """

  Scenario: Get all companies when there is one company

    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "name": "Neo Energia Companhia de Energia S.A."
    }
    """

    When I GET "/company"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
        "name": "Neo Energia Companhia de Energia S.A."
      }
    ]
    """

  Scenario: Get all companies when there are multiple companies

    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "name": "Neo Energia Companhia de Energia S.A."
    }
    """

    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7847",
      "name": "Companhia Energética de Minas Gerais"
    }
    """

    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7848",
      "name": "Light Serviços de Eletricidade S.A."
    }
    """

    When I GET "/company"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
        "name": "Neo Energia Companhia de Energia S.A."
      },
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7847",
        "name": "Companhia Energética de Minas Gerais"
      },
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7848",
        "name": "Light Serviços de Eletricidade S.A."
      }
    ]
    """

