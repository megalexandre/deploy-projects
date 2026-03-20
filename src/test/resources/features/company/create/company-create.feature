Feature: Company

  Background:
    Given I am authenticated

  Scenario: Create a company

    When I POST the payload to "/company" with body:
    """
    {
       "name": "Neo Energia Companhia de Energia S.A."
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "Neo Energia Companhia de Energia S.A."
    }
    """
    Then the table "company" should have 1 records

  Scenario: Create a company with minimum length name

    When I POST the payload to "/company" with body:
    """
    {
       "name": "Neo"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "Neo"
    }
    """
    Then the table "company" should have 1 records

  Scenario: Create a company with minimum length name

    When I POST the payload to "/company" with body:
    """
    {
       "name": "Neo"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "Neo"
    }
    """
    Then the table "company" should have 1 records


  Scenario: Create a company with maximum length name (256 characters)

    When I POST the payload to "/company" with body:
    """
    {
       "name": "Empresa com o nome realmente muito grande, olha só pra isso!!!"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "Empresa com o nome realmente muito grande, olha só pra isso!!!"
    }
    """
    Then the table "company" should have 1 records

  Scenario: Create a company with spaces in the name

    When I POST the payload to "/company" with body:
    """
    {
       "name": "  Empresa [espacos no inicio ou no final são removidos]   "
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "Empresa [espacos no inicio ou no final são removidos]"
    }
    """
    Then the table "company" should have 1 records