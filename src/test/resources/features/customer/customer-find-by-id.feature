Feature: Customer Find By Id

  Background:
    Given I am authenticated

  Scenario: Get by id a customer that exists

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

    Given the following customer exists in the database:
    """
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "addressId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "name": "João da Silva",
      "taxId": "12345678901",
      "phone": "11987654321",
      "email": "joao.silva@example.com",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """


    When I GET "/customers/550e8400-e29b-41d4-a716-446655440001"
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "nome": "João da Silva",
      "endereco":
      {
        "id":"019ca71b-3183-7a8b-8f71-e44a327a7846",
        "link":"https://maps.google.com/?q=-11.123456,-40.123456",
        "place":"any name",
        "cep":"41234567",
        "number":"12",
        "address":"any name",
        "complement":"description",
        "neighborhood":"jacobina III",
        "city":"jacobina",
        "state":"ba"
      },

      "cpfCnpj": "12345678901",
      "telefone": "11987654321",
      "email": "joao.silva@example.com",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

  Scenario: Get by id a customer that does not exist

    When I GET "/customers/550e8400-e29b-41d4-a716-446655440099"
    Then the response status code should be 404
