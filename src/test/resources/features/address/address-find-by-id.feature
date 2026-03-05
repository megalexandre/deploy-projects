Feature: Address

  Background:
    Given I am authenticated

  Scenario: Create a address with all fields

    Given the following address exists in the database:
    """
    {
       "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
       "cep": "41234567",
       "place": "any name",
       "number": "12",
       "address": "any name",
       "complement": "description",
       "neighborhood": "jacobina III",
       "city": "jacobina",
       "state": "ba",
       "link": "https://maps.google.com/?q=-11.123456,-40.123456"
    }
    """

    When I GET "/address/019ca71b-3183-7a8b-8f71-e44a327a7846"
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "cep": "41234567",
      "number": "12",
      "place": "any name",
      "address": "any name",
      "complement": "description",
      "neighborhood": "jacobina III",
      "city": "jacobina",
      "state": "ba",
      "link": "https://maps.google.com/?q=-11.123456,-40.123456"
    }
    """
