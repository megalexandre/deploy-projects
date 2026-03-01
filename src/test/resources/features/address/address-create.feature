Feature: Customer

  Background:
    Given I am authenticated

  Scenario: Create a customer with all fields

    When I POST the payload to "/address" with body:
    """
    {
       "name": "João da Silva",
       "link":  "https://www.linkedin.com/in/joaodasilva",
       "place": "string"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "João da Silva",
      "link": "https://www.linkedin.com/in/joaodasilva",
      "place": "string",
      "fullAddress": "João da Silva string https://www.linkedin.com/in/joaodasilva"
    }
    """
    Then the table "address" should have 1 records
