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
      "name": "Neo Energia Companhia de Energia S.A.",
      "active": true
    }
    """

    When I GET "/company"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
        "name": "Neo Energia Companhia de Energia S.A.",
        "acronym": null,
        "code": null,
        "region": null,
        "phone": null,
        "email": null,
        "active": true
      }
    ]
    """

  Scenario: Get all companies when there are multiple companies

    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "name": "Neo Energia Companhia de Energia S.A.",
      "active": true
    }
    """

    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7847",
      "name": "Companhia Energética de Minas Gerais",
      "active": true
    }
    """

    Given the following company exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7848",
      "name": "Light Serviços de Eletricidade S.A.",
      "active": true
    }
    """

    When I GET "/company"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
        "name": "Neo Energia Companhia de Energia S.A.",
        "acronym": null,
        "code": null,
        "region": null,
        "phone": null,
        "email": null,
        "active": true
      },
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7847",
        "name": "Companhia Energética de Minas Gerais",
        "acronym": null,
        "code": null,
        "region": null,
        "phone": null,
        "email": null,
        "active": true
      },
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7848",
        "name": "Light Serviços de Eletricidade S.A.",
        "acronym": null,
        "code": null,
        "region": null,
        "phone": null,
        "email": null,
        "active": true
      }
    ]
    """
