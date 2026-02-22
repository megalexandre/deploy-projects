Feature: Customer Delete

  Background:
    Given I am authenticated

  Scenario: Delete an existing customer

    Given the following customer exists in the database:
    """
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "name": "João da Silva",
      "taxId": "12345678901",
      "phone": "11987654321",
      "email": "joao.silva@example.com",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

    When I DELETE "/customers/550e8400-e29b-41d4-a716-446655440001"
    Then the response status code should be 204
    Then the table "customers" should have 0 records

  Scenario: Delete a customer that does not exist

    When I DELETE "/customers/550e8400-e29b-41d4-a716-446655440099"
    Then the response status code should be 204
