Feature: Customer

  Background:
    Given I am authenticated

  Scenario: Create a customer with all fields

    When I POST the payload to "/address" with body:
    """
    {
       "cep": "41234567",
       "number": "12",
       "address": "any name",
       "complement": "description",
       "neighborhood": "jacobina III",
       "city": "jacobina",
       "state": "ba",
       "link": "https://maps.google.com/?q=-11.123456,-40.123456"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "cep": "41234567",
      "number": "12",
      "address": "any name",
      "complement": "description",
      "neighborhood": "jacobina III",
      "city": "jacobina",
      "state": "ba",
      "link": "https://maps.google.com/?q=-11.123456,-40.123456"
    }
    """
    Then the table "address" should have 1 records
